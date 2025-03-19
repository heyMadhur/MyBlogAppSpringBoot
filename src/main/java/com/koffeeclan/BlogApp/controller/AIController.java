package com.koffeeclan.BlogApp.controller;

import com.koffeeclan.BlogApp.ai.AI;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/ai")
public class AIController {

    private final AI ai;

    public AIController(AI ai) {
        this.ai= ai;
    }


    @GetMapping
    public ResponseEntity<String> chatWithModel(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message){
        String response= ai.messageAI(message);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return ai.streamAI(message);
    }

}
