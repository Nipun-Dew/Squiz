package com.example.squiz.controllers;

import com.example.squiz.dtos.QuizRequest;
import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}")
public class QuizController {
    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/quiz/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable String id) {
        return service.getQuiz(id);
    }

    @GetMapping("/quiz/creator/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<QuizResponse>> getQuizzesByCreator(@PathVariable String id) {
        return service.findQuizForCreator(Integer.parseInt(id));
    }

    @PostMapping("/quiz")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> createQuiz(@RequestBody QuizRequest quiz) {
        return service.createNewQuiz(quiz);
    }
}
