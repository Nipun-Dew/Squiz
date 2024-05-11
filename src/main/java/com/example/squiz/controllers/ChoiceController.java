package com.example.squiz.controllers;

import com.example.squiz.dtos.ChoiceRequest;
import com.example.squiz.dtos.ChoiceResponse;
import com.example.squiz.services.ChoiceService;
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
public class ChoiceController implements UserDetailsUtil {
    private final ChoiceService choiceService;

    @Autowired
    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping("/choice/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ChoiceResponse> getChoiceById(@PathVariable String id) {
        return choiceService.getChoice(id);
    }

    @GetMapping("/choice/question/{questionId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<ChoiceResponse>> getChoicesForQuestion(@PathVariable String questionId) {
        return choiceService.getChoicesForQuestion(questionId);
    }

    @PostMapping("/choice")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> createChoice(@RequestBody ChoiceRequest choiceRequest,
                                                Authentication authentication) {

        String username = extractUser(authentication);
        return choiceService.createNewChoice(choiceRequest,username);
    }
}
