package com.arutha.controller.objectidentifier;

import com.arutha.api.request.objectidentifier.ObjectIdentifierScoreRequest;
import com.arutha.api.response.objectidentifier.ScoreResponse;
import com.arutha.model.objectidentifier.ObjectIdentifierScore;
import com.arutha.service.objectidentifier.ObjectIdentifierScoreService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Response class for Object Identifier score controller.
 */

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
