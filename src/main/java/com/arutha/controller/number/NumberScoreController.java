package com.arutha.controller.number;

import com.arutha.api.request.number.SaveScoreRequest;
import com.arutha.api.response.number.ScoreResponse;
import com.arutha.model.number.NumberScore;
import com.arutha.service.number.NumberScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Allow requests from your frontend origin

public class NumberScoreController {
    private final NumberScoreService numberScoreService;

    @PostMapping("/save")
    public ResponseEntity<ScoreResponse> saveScore(@RequestBody SaveScoreRequest request) {
        NumberScore savedScore = numberScoreService.saveScore(
                request.getUserId(),
                request.getScore()
        );

        ScoreResponse response = mapToResponse(savedScore);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

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
                .user_id(score.getId())
                .score(score.getScore())
                .create_at(score.getCreatedAt())
                .build();
    }
}
