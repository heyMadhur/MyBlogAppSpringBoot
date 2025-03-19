package com.koffeeclan.BlogApp.dto;

import com.koffeeclan.BlogApp.entity.BlogImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;


public class BlogDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @NotBlank(message = "Author name is required")
    private String authorName;

    private List<BlogImage> images;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Title is required") @Size(max = 255, message = "Title must be less than 255 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") @Size(max = 255, message = "Title must be less than 255 characters") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Content is required") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "Content is required") String content) {
        this.content = content;
    }

    public @NotBlank(message = "Author name is required") String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(@NotBlank(message = "Author name is required") String authorName) {
        this.authorName = authorName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<BlogImage> getImages() {
        return images;
    }

    public void setImages(List<BlogImage> images) {
        this.images = images;
    }

    public BlogDTO(Long id, String title, String content, String authorName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BlogDTO(Long id, String title, String content, String authorName, List<BlogImage> images, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.images = images;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public  BlogDTO(){}
}
