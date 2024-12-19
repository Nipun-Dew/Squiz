package com.example.squiz.controllers;

import com.example.squiz.dtos.QuestionsRequest;
import com.example.squiz.dtos.info.QuestionsInfoResponse;
import com.example.squiz.services.QuestionsService;
import com.example.squiz.utils.UserDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}")
public class QuestionsController implements UserDetailsUtil {
    private final QuestionsService questionsService;

    @Autowired
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping("/question/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<QuestionsInfoResponse> getQuestionById(@PathVariable String id) {
        return questionsService.getQuestionInfo(id);
    }

    @GetMapping("/question/quiz/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<QuestionsInfoResponse>> getQuestionsForQuiz(@PathVariable String id) {
        return questionsService.findQuestionsByQuiz(id);
    }

    @PostMapping("/question")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> createQuestion(@RequestBody QuestionsRequest question,
                                                  Authentication authentication) {

        String username = extractUser(authentication);
        return questionsService.createNewQuestion(question, username);
    }
}
