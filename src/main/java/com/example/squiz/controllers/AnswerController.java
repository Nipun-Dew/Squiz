package com.example.squiz.controllers;

import com.example.squiz.dtos.SessionRequest;
import com.example.squiz.dtos.SessionResponse;
import com.example.squiz.dtos.AnswersRequest;
import com.example.squiz.dtos.AnswersResponse;
import com.example.squiz.services.SessionsService;
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
    private final SessionsService sessionsService;
    private final AnswersService answerService;

    @Autowired
    public AnswerController(SessionsService sessionsService,
                            AnswersService answerService) {
        this.sessionsService = sessionsService;
        this.answerService = answerService;
    }

    @GetMapping("/session/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable String id) {
        return sessionsService.getSession(id);
    }

    @GetMapping("/session/quiz/{quizId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<SessionResponse>> getSessionsForQuiz(@PathVariable String quizId) {
        return sessionsService.getSessionsForQuiz(quizId);
    }

    @GetMapping("/session/answer/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<AnswersResponse> getAnswerById(@PathVariable String id) {
        return answerService.getAnswer(id);
    }

    @GetMapping("/session/answers/{sessionId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<AnswersResponse>> getAnswersForSession(@PathVariable String sessionId) {
        return answerService.getAnswersForSessionId(sessionId);
    }

    @PostMapping("/session")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SessionResponse> createSession(@RequestBody SessionRequest sessionRequest,
                                                         Authentication authentication) {
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        }

        return sessionsService.createNewSession(sessionRequest, username);
    }

    @PostMapping("/session/answer")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> createAnswer(@RequestBody AnswersRequest answerRequest,
                                                Authentication authentication) {

        String username = extractUser(authentication);
        return answerService.createNewAnswer(answerRequest, username);
    }
}
