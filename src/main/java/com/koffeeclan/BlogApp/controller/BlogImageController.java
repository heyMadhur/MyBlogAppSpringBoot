package com.koffeeclan.BlogApp.controller;

import com.koffeeclan.BlogApp.dto.BlogImageDTO;
import com.koffeeclan.BlogApp.entity.BlogImage;
import com.koffeeclan.BlogApp.service.blogs.BlogImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/blogimages")
public class BlogImageController {

    private final BlogImageService blogImageService;

    public BlogImageController(BlogImageService blogImageService) {
        this.blogImageService = blogImageService;
    }

    @PostMapping("/blog/{id}")
    public ResponseEntity<BlogImage> addBlogImage(@RequestParam("image") MultipartFile blogImage, @PathVariable Long id) {

        BlogImage createdImage = blogImageService.addBlogImage(blogImage, id);

        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogImage> getBlogImageById(@PathVariable Long id) {
        BlogImage blogImage = blogImageService.getBlogImageById(id);
        return ResponseEntity.ok(blogImage);
    }

    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<BlogImage>> getImagesByBlog(@PathVariable Long blogId) {
        List<BlogImage> images = blogImageService.getImagesByBlogId(blogId);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogImage(@PathVariable Long id) {
        blogImageService.deleteBlogImage(id);
        return ResponseEntity.noContent().build();
    }
}
