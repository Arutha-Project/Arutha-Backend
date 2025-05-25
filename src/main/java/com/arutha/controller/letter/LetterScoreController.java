package com.arutha.controller.letter;

import com.arutha.model.letter.LetterScore;
import com.arutha.service.letter.LetterScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/letter-scores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LetterScoreController {

    private final LetterScoreService service;


    @PostMapping("/")
    public ResponseEntity<LetterScore> save(@RequestBody LetterScore score) {
        return ResponseEntity.ok(service.saveScore(score));
    }

    @GetMapping
    public ResponseEntity<List<LetterScore>> getAll() {
        return ResponseEntity.ok(service.getAllScores());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LetterScore>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<LetterScore>> getByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(service.getByLanguage(language));
    }
}
