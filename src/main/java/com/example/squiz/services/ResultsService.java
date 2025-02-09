package com.example.squiz.services;

import com.example.squiz.dtos.ResultsResponse;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.QuizEB;
import com.example.squiz.entities.ResultsEB;
import com.example.squiz.entities.SessionsEB;
import com.example.squiz.exceptions.customExceptions.BadRequestException;
import com.example.squiz.exceptions.customExceptions.InternalServerErrorException;
import com.example.squiz.exceptions.customExceptions.NoContentException;
import com.example.squiz.repos.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static java.lang.Long.parseLong;

@Service
public class ResultsService {
    private final ResultsRepository resultsRepository;

    @Autowired
    public ResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public void generateResults(SessionsEB session) {
        QuizEB quiz = session.getQuiz();
        Set<AnswersEB> answers = session.getAnswers();

        LocalDateTime startTime = session.getStartTime();
        LocalDateTime endTime = session.getEndTime();

        Duration duration = Duration.between(startTime, endTime);
        Integer timeTakenInSeconds = Math.toIntExact(duration.getSeconds());

        Integer correctAnswersCount = 0;
        Integer incorrectAnswersCount = 0;

        for (AnswersEB answer : answers) {
            if (answer.getIsCorrectAnswer()) {
                correctAnswersCount++;
            } else {
                incorrectAnswersCount++;
            }
        }

        ResultsEB resultsEB = new ResultsEB();
        resultsEB.setQuiz(quiz);
        resultsEB.setSession(session);
        resultsEB.setNumberOfCorrectAnswers(correctAnswersCount);
        resultsEB.setNumberOfIncorrectAnswers(incorrectAnswersCount);
        resultsEB.setTimeTaken(timeTakenInSeconds);

        resultsRepository.save(resultsEB);
    }

    public ResponseEntity<ResultsResponse> getResults(String sessionId, String username) {
        try {
            Long sessionIdLong = parseLong(sessionId);
            ResultsEB resultsEB = resultsRepository.getResultsBySession_Id(sessionIdLong)
                    .orElseThrow(() -> new NoContentException("No results found for session id: " + sessionId));

            ResultsResponse resultsResponse = new ResultsResponse().createResultsResponse(resultsEB);
            return ResponseEntity.ok(resultsResponse);

        } catch (NoContentException e) {
            throw new NoContentException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid session ID format: " + sessionId);
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred while fetching results: " + e.getMessage());
        }
    }
}
