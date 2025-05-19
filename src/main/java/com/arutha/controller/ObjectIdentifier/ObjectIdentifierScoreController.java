package com.arutha.controller.ObjectIdentifier;

import com.arutha.api.request.ObjectIdentifier.ObjectIdentifierScoreRequest;
import com.arutha.api.response.ObjectIdentifier.ScoreResponse;
import com.arutha.model.ObjectIdentifier.ObjectIdentifierScore;
import com.arutha.service.ObjectIdentifier.ObjectIdentifierScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@RequestMapping("/object-identifier-scores")
public class ObjectIdentifierScoreController {

    private final ObjectIdentifierScoreService scoreService;


    @PostMapping
    public ResponseEntity<ScoreResponse> saveScore(@Valid @RequestBody ObjectIdentifierScoreRequest request) {
        ObjectIdentifierScore savedScore = scoreService.saveScore(request);

        ScoreResponse response = new ScoreResponse();
        response.setId(savedScore.getId());  
        response.setUserId(savedScore.getUser().getId());  
        response.setCategory(savedScore.getCategory());
        response.setScore(savedScore.getScore());
        response.setTotalItems(savedScore.getTotalItems());
        response.setCreatedAt(savedScore.getCreatedAt());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScoreResponse>> getScoresByUserId(@PathVariable Integer userId) {
        List<ObjectIdentifierScore> scores = scoreService.getScoresByUserId(userId);

        List<ScoreResponse> responseList = scores.stream()
                .map(score -> {
                    ScoreResponse response = new ScoreResponse();
                    response.setId(score.getId());
                    response.setUserId(score.getUser().getId());
                    response.setCategory(score.getCategory());
                    response.setScore(score.getScore());
                    response.setTotalItems(score.getTotalItems());
                    response.setCreatedAt(score.getCreatedAt());
                    return response;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}
