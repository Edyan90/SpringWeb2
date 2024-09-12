package epicode.wsSpringBot2.controllers;

import epicode.wsSpringBot2.entities.Blog;
import epicode.wsSpringBot2.entities.BlogPostBody;
import epicode.wsSpringBot2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogController {

    //importiamoci il service per avere i metodi
    @Autowired
    private BlogService bs;


    //    1. GET http://localhost:3001/blogPosts
    @GetMapping
    private List<Blog> findAll() {
        return bs.findAll();
    }

    //            2. GET  http://localhost:3001/blogPosts/{userId}
    @GetMapping("/{blogID}")
    private Blog findByID(@PathVariable UUID blogID) {
        return bs.findByID(blogID);
    }

    //            3. POST http://localhost:3001/blogPosts (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Blog createBlog(@RequestBody BlogPostBody body) {
        return bs.save(body);
    }

    //            4. PUT http://localhost:3001/blogPosts/{userId} (+ body)
    @PutMapping("/{blogID}")
    private Blog findandupdate(@PathVariable UUID blogID, @RequestBody Blog updateBlog) {
        return bs.update(blogID, updateBlog);
    }

    // 5. DELETE http://localhost:3001/blogPosts/{userId}
    @DeleteMapping("/{blogID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findandDelete(@PathVariable UUID blogID) {
        bs.findByIDandDelete(blogID);
    }

}
