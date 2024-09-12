package epicode.wsSpringBot2.services;


import epicode.wsSpringBot2.entities.Author;
import epicode.wsSpringBot2.entities.Blog;
import epicode.wsSpringBot2.entities.BlogPostBody;
import epicode.wsSpringBot2.exceptions.NotFoundEx;
import epicode.wsSpringBot2.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BlogRepository blogRepository;


    public List<Blog> findAll() {
        return this.blogRepository.findAll();
    }

    public Blog findByID(UUID blogID) {
        Blog found = blogRepository.findById(blogID).orElseThrow(() -> new NotFoundEx(blogID));
        return found;

    }

    public Blog save(BlogPostBody blogBody) {
        Author autore = authorService.findByID(blogBody.getAutoreID());
        Blog blog = new Blog(blogBody.getCategoria(), blogBody.getTitolo(), blogBody.getContenuto(), blogBody.getTempoDiLettura(), autore);
        blog.setCover("https://picsum.photos/id/1/200/300");
        return this.blogRepository.save(blog);
    }

    public Blog update(UUID blogID, Blog blogUpdate) {
        Blog found = findByID(blogID);
        found.setCategoria(blogUpdate.getCategoria());
        found.setTitolo(blogUpdate.getTitolo());
        found.setContenuto(blogUpdate.getContenuto());
        found.setTempoDiLettura(blogUpdate.getTempoDiLettura());
        Author autore = authorService.findByID(blogUpdate.getId());
        found.setAuthor(autore);

        return found;
    }

    public void findByIDandDelete(UUID blogID) {
        Blog found = findByID(blogID);
        this.blogRepository.delete(found);
    }
}
