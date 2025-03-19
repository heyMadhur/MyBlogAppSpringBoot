package com.koffeeclan.BlogApp.controller;

import com.koffeeclan.BlogApp.ai.AI;
import com.koffeeclan.BlogApp.dto.BlogDTO;
import com.koffeeclan.BlogApp.entity.Blog;
import com.koffeeclan.BlogApp.service.blogs.BlogService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/blogs")
public class BlogController {
    private final BlogService blogService;
    private final AI ai;

    public BlogController(BlogService blogService, AI ai) {
        this.blogService= blogService;
        this.ai= ai;
    }

    @PostMapping
    public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
        Blog createdBlog = blogService.createBlog(blogService.toBlog(blogDTO));
        return new ResponseEntity<>(blogService.toBlogDTO(createdBlog), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        if(blog!=null)
            return ResponseEntity.ok(blogService.toBlogDTO(blog));
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<BlogDTO>> getAllBlogs(Pageable pageable) {
        Page<Blog> blogs = blogService.getAllBlogs(pageable);
        Page<BlogDTO> blogDTOs = blogs.map(blogService::toBlogDTO);
        return ResponseEntity.ok(blogDTOs);
    }

    @GetMapping("/{id}/ai-summarise")
    public ResponseEntity<String> summarizeBlog(@PathVariable Long id){
        return ResponseEntity.ok(ai.summariseBlog(id));
    }

    @GetMapping(value="/{id}/ai-summarise/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> summarizeBlogStream(@PathVariable Long id){
        return ai.summariseBlogStream(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @RequestBody BlogDTO blogDTO) {
        System.out.println("UPdate Blog Page Received");
        Blog updatedBlog = blogService.updateBlog(id, blogService.toBlog(blogDTO));
        System.out.println("Updated Blog "+ updatedBlog );
        if(updatedBlog!=null)
            return ResponseEntity.ok(blogService.toBlogDTO(updatedBlog));
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
