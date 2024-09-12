package epicode.wsSpringBot2.controllers;

import epicode.wsSpringBot2.entities.Author;
import epicode.wsSpringBot2.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    //importiamoci il service per avere i metodi
    @Autowired
    private AuthorService as;


    //1. GET http://localhost:3001/blogPosts
    @GetMapping
    private Page<Author> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "nome") String sortBy) {
        return as.findAll(page, size, sortBy);
    }

    // 2. GET  http://localhost:3001/blogPosts/{userId}
    @GetMapping("/{authorID}")
    private Author findByID(@PathVariable UUID authorID) {
        return as.findByID(authorID);
    }

    //3. POST http://localhost:3001/blogPosts (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Author createBlog(@RequestBody Author body) {
        return as.save(body);
    }

    //4. PUT http://localhost:3001/blogPosts/{userId} (+ body)
    @PutMapping("/{authorID}")
    private Author findandupdate(@PathVariable UUID authorID, @RequestBody Author updateBlog) {
        return as.update(authorID, updateBlog);
    }

    //5. DELETE http://localhost:3001/blogPosts/{userId}
    @DeleteMapping("/{authorID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findandDelete(@PathVariable UUID authorID) {
        as.findByIDandDelete(authorID);

    }
}
