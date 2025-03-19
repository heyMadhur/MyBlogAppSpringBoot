package com.koffeeclan.BlogApp.service.blogs;

import com.koffeeclan.BlogApp.dto.BlogImageDTO;
import com.koffeeclan.BlogApp.entity.BlogImage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BlogImageService {
    BlogImage addBlogImage(MultipartFile imageFile, Long id);

    String preSignedUrl(String fileName);

    BlogImage getBlogImageById(Long id);
    List<BlogImage> getImagesByBlogId(Long blogId);
    void deleteBlogImage(Long id);

    BlogImage toBlogImage(BlogImageDTO blogImageDTO);
}
