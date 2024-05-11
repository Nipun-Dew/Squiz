package com.example.squiz.controllers;

import com.example.squiz.dtos.AnswerSetRequest;
import com.example.squiz.dtos.AnswerSetResponse;
import com.example.squiz.dtos.AnswersRequest;
import com.example.squiz.dtos.AnswersResponse;
import com.example.squiz.services.AnswerSetService;
import com.example.squiz.services.AnswersService;
import com.example.squiz.utils.UserDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}")
public class AnswerController implements UserDetailsUtil {
    private final AnswerSetService answerSetService;
    private final AnswersService answerService;

    @Autowired
    public AnswerController(AnswerSetService answerSetService,
                            AnswersService answerService) {
        this.answerSetService = answerSetService;
        this.answerService = answerService;
    }

    @GetMapping("/submission/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<AnswerSetResponse> getAnswerSetById(@PathVariable String id) {
        return answerSetService.getAnswerSet(id);
    }

    @GetMapping("/submission/quiz/{quizId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<AnswerSetResponse>> getAnswerSetsForQuiz(@PathVariable String quizId) {
        return answerSetService.getAnswerSetsForQuiz(quizId);
    }

    @GetMapping("/submission/answer/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<AnswersResponse> getAnswerById(@PathVariable String id) {
        return answerService.getAnswer(id);
    }

    @GetMapping("/submission/answers/{answerSetId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<AnswersResponse>> getAnswersForAnswerSet(@PathVariable String answerSetId) {
        return answerService.getAnswersForAnswerSet(answerSetId);
    }

    @PostMapping("/submission")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> createSubmission(@RequestBody AnswerSetRequest answerSetRequest,
                                                    Authentication authentication) {
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        }

        return answerSetService.createNewAnswerSet(answerSetRequest, username);
    }

    @PostMapping("/submission/answer")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> createAnswer(@RequestBody AnswersRequest answerRequest,
                                                Authentication authentication) {

        String username = extractUser(authentication);
        return answerService.createNewAnswer(answerRequest, username);
    }
}
