package com.example.squiz.services;

import com.example.squiz.dtos.ChoiceResponse;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.repos.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChoiceService {
    private final ChoiceRepository choiceRepository;

    private final ChoiceResponse choiceResponse;

    @Autowired
    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
        this.choiceResponse = new ChoiceResponse();
    }

    public ResponseEntity<ChoiceResponse> getChoice(String id) {
        try {
            Optional<ChoicesEB> optionalChoice = choiceRepository.findById(Long.parseLong(id));

            return optionalChoice.map(choice -> ResponseEntity.ok(choiceResponse.createChoiceResponse(choice)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(choiceResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(choiceResponse);
        }
    }

    public ResponseEntity<List<ChoiceResponse>> getChoicesForQuestion(String questionId) {
        try {
            List<ChoicesEB> results = choiceRepository.getChoicesForQuestion(Long.parseLong(questionId));
            List<ChoiceResponse> choiceResponses = results.stream()
                    .map(result -> new ChoiceResponse().createChoiceResponse(result))
                    .toList();
            return ResponseEntity.ok(choiceResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
