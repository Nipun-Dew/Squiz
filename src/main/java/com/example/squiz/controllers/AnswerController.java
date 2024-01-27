package com.example.squiz.controllers;

import com.example.squiz.dtos.AnswerSetRequest;
import com.example.squiz.dtos.AnswerSetResponse;
import com.example.squiz.dtos.AnswersRequest;
import com.example.squiz.dtos.AnswersResponse;
import com.example.squiz.services.AnswerSetService;
import com.example.squiz.services.AnswersService;
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
    private final AnswersService answerService;

    @Autowired
    public AnswerController(AnswerSetService answerSetService,
                            AnswersService answerService) {
        this.answerSetService = answerSetService;
        this.answerService = answerService;
    }

    @GetMapping("/submission/{id}")
    public ResponseEntity<AnswerSetResponse> getAnswerSetById(@PathVariable String id) {
        return answerSetService.getAnswerSet(id);
    }

    @GetMapping("/submission/quiz/{quizId}")
    public ResponseEntity<List<AnswerSetResponse>> getAnswerSetsForQuiz(@PathVariable String quizId) {
        return answerSetService.getAnswerSetsForQuiz(quizId);
    }

    @GetMapping("/submission/answer/{id}")
    public ResponseEntity<AnswersResponse> getAnswerById(@PathVariable String id) {
        return answerService.getAnswer(id);
    }

    @GetMapping("/submission/answers/{answerSetId}")
    public ResponseEntity<List<AnswersResponse>> getAnswersForAnswerSet(@PathVariable String answerSetId) {
        return answerService.getAnswersForAnswerSet(answerSetId);
    }

    @PostMapping("/submission")
    public ResponseEntity<Integer> createSubmission(@RequestBody AnswerSetRequest answerSetRequest) {
        return answerSetService.createNewAnswerSet(answerSetRequest);
    }

    @PostMapping("/submission/answer")
    public ResponseEntity<Integer> createAnswer(@RequestBody AnswersRequest answerRequest) {
        return answerService.createNewAnswer(answerRequest);
    }
}
