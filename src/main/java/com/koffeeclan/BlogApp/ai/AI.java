package com.koffeeclan.BlogApp.ai;

import com.koffeeclan.BlogApp.entity.Blog;
import com.koffeeclan.BlogApp.service.blogs.BlogService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Component
public class AI {

    private final ChatModel chatModel;
    private final BlogService blogService;

    public AI(@Qualifier("ollamaChatModel")ChatModel chatModel, BlogService blogService) {
        this.chatModel= chatModel;
        this.blogService= blogService;
    }

    public String summariseBlog(Long id) {
        Blog blog= blogService.getBlogById(id);

        String message= "Generate a summary including all the important points for the Blog given. Blog-:";
        message+= "Title-: "+blog.getTitle();
        message+= "Content-: "+blog.getContent();
        message+= "Author-:" + blog.getAuthor().getUsername();

        System.out.println("Message for AI: "+message);

        Prompt prompt= new Prompt(
                message,
                OllamaOptions.builder()
                        .model(OllamaModel.MISTRAL)
                        .temperature(0.4)
                        .build()
        );
        ChatResponse response= chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

    public Flux<String> summariseBlogStream(Long id) {
        Blog blog= blogService.getBlogById(id);

        String message= "Generate a summary including all the important points for the Blog given. Blog-:";
        message+= "Title-: "+blog.getTitle();
        message+= "Content-: "+blog.getContent();
        message+= "Author-:" + blog.getAuthor().getUsername();
        System.out.println("Message for AI(Stream): "+message);

        Prompt prompt= new Prompt(
                message,
                OllamaOptions.builder()
                        .model(OllamaModel.MISTRAL)
                        .temperature(0.4)
                        .build()
        );
        return chatModel.stream(prompt).map(response -> response.getResult().getOutput().getText());
    }

    public String messageAI(String message){
        Prompt prompt= new Prompt(
                message,
                OllamaOptions.builder()
                        .model(OllamaModel.MISTRAL)
                        .temperature(0.4)
                        .build()
        );
        ChatResponse response = chatModel.call(prompt);
        System.out.println(response.getResult().getOutput().getText()); // Or however you want to use it
        return response.getResult().getOutput().getText();
    }

    public Flux<String> streamAI(String message) {
        Prompt prompt= new Prompt(
                message,
                OllamaOptions.builder()
                        .model(OllamaModel.MISTRAL)
                        .temperature(0.4)
                        .build()
        );
        return chatModel.stream(prompt).map(response -> response.getResult().getOutput().getText());
    }
}
