package com.koffeeclan.BlogApp.dto;

import com.koffeeclan.BlogApp.entity.Blog;
import com.koffeeclan.BlogApp.entity.BlogImage;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


public class BlogImageDTO {

    private Long id;

    private Blog blog;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public @NotBlank(message = "Image URL is required") String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NotBlank(message = "Image URL is required") String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BlogImageDTO(Long id, Blog blog, String imageUrl) {
        this.id = id;
        this.blog = blog;
        this.imageUrl = imageUrl;
    }

    public BlogImageDTO(){}
}
