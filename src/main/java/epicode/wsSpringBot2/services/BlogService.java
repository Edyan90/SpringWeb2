package epicode.wsSpringBot2.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicode.wsSpringBot2.RecordDTO.BlogPostBody;
import epicode.wsSpringBot2.entities.Author;
import epicode.wsSpringBot2.entities.Blog;
import epicode.wsSpringBot2.exceptions.NotFoundEx;
import epicode.wsSpringBot2.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private Cloudinary cloudinary;


    public List<Blog> findAll() {
        return this.blogRepository.findAll();
    }

    public Blog findByID(UUID blogID) {
        Blog found = blogRepository.findById(blogID).orElseThrow(() -> new NotFoundEx(blogID));
        return found;

    }

    public Blog save(BlogPostBody blogBody) {
        Author autore = authorService.findByID(blogBody.autoreID());
        Blog blog = new Blog(blogBody.categoria(), blogBody.titolo(), blogBody.contenuto(), blogBody.tempoDiLettura(), autore);
        blog.setCover("https://picsum.photos/id/1/200/300");
        return this.blogRepository.save(blog);
    }

    public Blog update(UUID blogID, BlogPostBody blogUpdate) {
        Blog found = findByID(blogID);
        found.setCategoria(blogUpdate.categoria());
        found.setTitolo(blogUpdate.titolo());
        found.setContenuto(blogUpdate.contenuto());
        found.setTempoDiLettura(blogUpdate.tempoDiLettura());
        Author autore = authorService.findByID(blogUpdate.autoreID());
        found.setAuthor(autore);

        return found;
    }

    public void findByIDandDelete(UUID blogID) {
        Blog found = findByID(blogID);
        this.blogRepository.delete(found);
    }

    public Blog uploadImage(UUID blogID, MultipartFile file) throws IOException {
        Blog blog = findByID(blogID);
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        System.out.println("URL: " + url);
        blog.setCover(url);
        blogRepository.save(blog);
        return blog;
    }
}
