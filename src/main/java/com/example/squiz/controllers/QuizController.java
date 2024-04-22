package com.example.squiz.controllers;

import com.example.squiz.dtos.QuizRequest;
import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Integer> createQuiz(@RequestBody QuizRequest quiz, Authentication authentication) {

        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }

        return service.createNewQuiz(quiz, username);
    }
}
