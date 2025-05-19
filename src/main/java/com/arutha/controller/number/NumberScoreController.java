package com.arutha.controller.number;

import com.arutha.api.request.number.SaveScoreRequest;
import com.arutha.api.response.number.ScoreResponse;
import com.arutha.model.number.NumberScore;
import com.arutha.service.number.NumberScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for handling number score operations.
 */
@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Allow requests from your frontend origin

public class NumberScoreController {
    private final NumberScoreService numberScoreService;

    /**
     * Save score.
     */
    @PostMapping("/save")
    public ResponseEntity<ScoreResponse> saveScore(@Valid @RequestBody SaveScoreRequest request) {
        NumberScore savedScore = numberScoreService.saveScore(
                request.getUserId(),
                request.getScore()
        );

        ScoreResponse response = mapToResponse(savedScore);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get save score.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScoreResponse>> getUserScores(@PathVariable Long userId) {
        List<NumberScore> scores = numberScoreService.getUserScores(userId);
        List<ScoreResponse> responseList = scores.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    private ScoreResponse mapToResponse(NumberScore score) {
        return ScoreResponse.builder()
                .id(score.getId())
                .userId(score.getId())
                .score(score.getScore())
                .createAt(score.getCreatedAt())
                .build();
    }
}
