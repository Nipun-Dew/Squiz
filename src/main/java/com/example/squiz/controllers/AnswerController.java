package com.example.squiz.controllers;

import com.example.squiz.dtos.AnswerSetRequest;
import com.example.squiz.dtos.AnswerSetResponse;
import com.example.squiz.services.AnswerSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}")
public class AnswerController {
    private final AnswerSetService answerSetService;

    @Autowired
    public AnswerController(AnswerSetService answerSetService) {
        this.answerSetService = answerSetService;
    }

    @GetMapping("/submission/{id}")
    public ResponseEntity<AnswerSetResponse> getAnswerSetById(@PathVariable String id) {
        return answerSetService.getAnswerSet(id);
    }

    @GetMapping("/submission/quiz/{quizId}")
    public ResponseEntity<List<AnswerSetResponse>> getChoicesForQuestion(@PathVariable String quizId) {
        return answerSetService.getAnswerSetsForQuiz(quizId);
    }

    @PostMapping("/submission")
    public ResponseEntity<Integer> createChoice(@RequestBody AnswerSetRequest answerSetRequest) {
        return answerSetService.createNewAnswerSet(answerSetRequest);
    }
}
