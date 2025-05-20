package com.arutha.controller.ObjectIdentifier;

import com.arutha.api.request.ObjectIdentifier.ObjectIdentifierScoreRequest;
import com.arutha.api.response.ObjectIdentifier.ScoreResponse;
import com.arutha.model.ObjectIdentifier.ObjectIdentifierScore;
import com.arutha.service.ObjectIdentifier.ObjectIdentifierScoreService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/object-identifier-scores")
public class ObjectIdentifierScoreController {

    private final ObjectIdentifierScoreService scoreService;

    @PostMapping
    public ResponseEntity<ObjectIdentifierScore> saveScore(@Valid @RequestBody ObjectIdentifierScoreRequest request) {
       scoreService.saveScore(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScoreResponse>> getScoresByUserId(@PathVariable Integer userId) {
        List<ScoreResponse> scores = scoreService.getScoresByUserId(userId);
        return ResponseEntity.ok(scores);
    }
}
