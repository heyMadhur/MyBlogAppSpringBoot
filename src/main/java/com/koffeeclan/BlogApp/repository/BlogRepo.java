package com.koffeeclan.BlogApp.repository;

import com.koffeeclan.BlogApp.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
}
