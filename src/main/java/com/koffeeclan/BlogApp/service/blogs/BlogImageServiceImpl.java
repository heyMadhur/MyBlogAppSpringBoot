package com.koffeeclan.BlogApp.service.blogs;

import com.koffeeclan.BlogApp.dto.BlogImageDTO;
import com.koffeeclan.BlogApp.entity.Blog;
import com.koffeeclan.BlogApp.entity.BlogImage;
import com.koffeeclan.BlogApp.repository.BlogImageRepo;
import com.koffeeclan.BlogApp.repository.BlogRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class BlogImageServiceImpl implements  BlogImageService {

    private final BlogImageRepo blogImageRepo;
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final BlogRepo blogRepo;

    @Value("${app.s3.bucket}")
    private String bucketName;

    public BlogImageServiceImpl(BlogImageRepo blogImageRepo, S3Client s3Client, S3Presigner s3Presigner, BlogRepo blogRepo) {
        this.blogImageRepo= blogImageRepo;
        this.s3Client = s3Client;
        this.s3Presigner= s3Presigner;
        this.blogRepo= blogRepo;
    }


    @Override
    public BlogImage addBlogImage(MultipartFile imageFile, Long id) {
        String fileName=imageFile.getOriginalFilename();
        if(fileName==null)
            throw new RuntimeException("File is null");

        fileName= UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        try {
            PutObjectRequest putObjectRequest= PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(imageFile.getInputStream(), imageFile.getSize()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String imageUrl= preSignedUrl(fileName);

        Blog blog = blogRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));

        BlogImage blogImage= new BlogImage();
        blogImage.setImageUrl(imageUrl);
        blogImage.setBlog(blog);

        List<BlogImage> list= blog.getBlogImages();
        list.add(blogImage);

        return blogImageRepo.save(blogImage);
    }

    @Override
    public String preSignedUrl(String fileName) {

        GetObjectRequest getObjectRequest= GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        GetObjectPresignRequest presignReq= GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(1))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedReq= s3Presigner.presignGetObject(presignReq);

        return presignedReq.url().toString();
    }

    @Override
    public BlogImage getBlogImageById(Long id) {
        return blogImageRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Blog image not found with id: " + id));
    }

    @Override
    public List<BlogImage> getImagesByBlogId(Long blogId) {
        return blogImageRepo.findByBlogId(blogId);
    }

    @Override
    public void deleteBlogImage(Long id) {
        BlogImage blogImage = getBlogImageById(id);
        blogImageRepo.delete(blogImage);
    }

    @Override
    public BlogImage toBlogImage(BlogImageDTO blogImageDTO) {
        BlogImage blogImage= new BlogImage();
        blogImage.setImageUrl(blogImageDTO.getImageUrl());
        return blogImage;
    }
}
