package com.koffeeclan.BlogApp.repository;

import com.koffeeclan.BlogApp.entity.BlogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogImageRepo extends JpaRepository<BlogImage, Long> {

    List<BlogImage> findByBlogId(Long blogId);

}
