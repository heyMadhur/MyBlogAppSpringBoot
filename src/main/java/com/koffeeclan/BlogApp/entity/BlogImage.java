package com.koffeeclan.BlogApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
public class BlogImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(nullable = false, columnDefinition = "text")
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BlogImage(Long id, Blog blog, String imageUrl, String description) {
        this.id = id;
        this.blog = blog;
        this.imageUrl = imageUrl;
    }
    public BlogImage(){}

    @Override
    public String toString() {
        return "BlogImage{" +
                "id=" + id +
//                ", blog=" + blog +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}