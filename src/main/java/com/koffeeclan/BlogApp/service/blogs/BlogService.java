package com.koffeeclan.BlogApp.service.blogs;

import com.koffeeclan.BlogApp.dto.BlogDTO;
import com.koffeeclan.BlogApp.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {

    Blog createBlog(Blog blog);

    Blog getBlogById(Long id);

    Page<Blog> getAllBlogs(Pageable pageable);

    Blog updateBlog(Long id, Blog updatedBlog);

    void deleteBlog(Long id);

    Blog toBlog(BlogDTO blogDTO);

    BlogDTO toBlogDTO(Blog blog);

}
