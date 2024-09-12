package epicode.wsSpringBot2.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicode.wsSpringBot2.RecordDTO.NewAuthorDTO;
import epicode.wsSpringBot2.entities.Author;
import epicode.wsSpringBot2.exceptions.BadRequestEx;
import epicode.wsSpringBot2.exceptions.NotFoundEx;
import epicode.wsSpringBot2.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Cloudinary cloudinary;

    //    public List<Author> findAll() {
//        return this.authorRepository.findAll();
//    }
    public Page<Author> findAll(int page, int size, String sortBy) {
        if (page > 20) page = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorRepository.findAll(pageable);
    }

    public Author findByID(UUID authorID) {
        return this.authorRepository.findById(authorID).orElseThrow(() -> new NotFoundEx(authorID));
    }

    public Author save(NewAuthorDTO body) {
        this.authorRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestEx("L'email " + body.email() + " è già in uso!");
        });
        Author newAuthor = new Author(body.nome(),
                body.cognome(),
                body.email(),
                body.dataDiNascita()
        );
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());

        return this.authorRepository.save(newAuthor);
    }

    public Author update(UUID authorID, NewAuthorDTO authorUpdate) {
        Author found = findByID(authorID);
        found.setNome(authorUpdate.nome());
        found.setCognome(authorUpdate.cognome());
        found.setEmail(authorUpdate.email());
        found.setDataDiNascita(authorUpdate.dataDiNascita());
        return authorRepository.save(found);
    }

    public void findByIDandDelete(UUID authorID) {
        Author found = findByID(authorID);
        authorRepository.delete(found);
        System.out.println("eliminato!");
    }

    public Author uploadImage(UUID authorID, MultipartFile file) throws IOException {
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        System.out.println("URL: " + url);
        Author autore = findByID(authorID);
        autore.setAvatar(url);
        return autore;
    }
}
