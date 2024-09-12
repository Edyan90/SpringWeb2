package epicode.wsSpringBot2.services;


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

import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

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

    public Author save(Author body) {
        this.authorRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestEx("L'email " + body.getEmail() + " è già in uso!");
        });
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
        return this.authorRepository.save(body);
    }

    public Author update(UUID authorID, Author authorUpdate) {
        Author found = findByID(authorID);
        found.setNome(authorUpdate.getNome());
        found.setCognome(authorUpdate.getCognome());
        found.setEmail(authorUpdate.getEmail());
        found.setDataDiNascita(authorUpdate.getDataDiNascita());
        return authorRepository.save(found);
    }

    public void findByIDandDelete(UUID authorID) {
        Author found = findByID(authorID);
        authorRepository.delete(found);
        System.out.println("eliminato!");
    }
}
