package com.example.squiz.controllers;

import com.example.squiz.dtos.ResultsResponse;
import com.example.squiz.services.ResultsService;
import com.example.squiz.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api.prefix}")
public class ResultsController implements AuthenticationUtil {
    private final ResultsService resultsService;

    @Autowired
    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping(value = "/results/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResultsResponse> getResultsBySessionId(@PathVariable String sessionId, Authentication authentication) {
        String username = getUsername(authentication);
        return resultsService.getResults(sessionId, username);
    }
}
