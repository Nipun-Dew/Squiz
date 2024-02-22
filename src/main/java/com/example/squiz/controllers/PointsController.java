package com.example.squiz.controllers;

import com.example.squiz.services.AnswerSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api.prefix}")
public class PointsController {
    private final AnswerSetService answerSetService;

    @Autowired
    public PointsController(AnswerSetService answerSetService) {
        this.answerSetService = answerSetService;
    }

    @GetMapping("/points/submission/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> getPointsBySubmissionId(@PathVariable String id) {
        return answerSetService.getPointsForAnswerSet(id);
    }
}
