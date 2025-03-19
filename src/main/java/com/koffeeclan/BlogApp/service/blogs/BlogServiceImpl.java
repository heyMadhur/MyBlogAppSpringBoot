package com.koffeeclan.BlogApp.service.blogs;

import com.koffeeclan.BlogApp.dto.BlogDTO;
import com.koffeeclan.BlogApp.entity.Blog;
import com.koffeeclan.BlogApp.entity.Users;
import com.koffeeclan.BlogApp.repository.BlogRepo;
import com.koffeeclan.BlogApp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl  implements BlogService {
    private final BlogRepo blogRepo;
    private final UserService userService;


    public BlogServiceImpl(BlogRepo blogRepo, UserService userService) {
        this.blogRepo= blogRepo;
        this.userService= userService;

    }

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));
    }

    @Override
    public Page<Blog> getAllBlogs(Pageable pageable) {
        return blogRepo.findAll(pageable);
    }

    @Override
    public Blog updateBlog(Long id, Blog updatedBlog) {
        Blog oldBlog= getBlogById(id);
        oldBlog.setTitle(updatedBlog.getTitle());
        oldBlog.setContent(updatedBlog.getContent());
        return blogRepo.save(oldBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        Blog blog= getBlogById(id);
        blogRepo.delete(blog);
    }

    public Blog toBlog(BlogDTO blogDTO){
        Blog blog = new Blog();
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        Users author = userService.getUserByUsername(blogDTO.getAuthorName());
        blog.setAuthor(author);
        return blog;
    }

    public BlogDTO toBlogDTO(Blog blog) {
        BlogDTO blogDTO= new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setAuthorName(blog.getAuthor().getUsername());
        blogDTO.setContent(blog.getContent());
        blogDTO.setCreatedAt(blog.getCreatedAt());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setUpdatedAt(blog.getUpdatedAt());
        blogDTO.setImages(blog.getBlogImages());

        return blogDTO;
    }
}
