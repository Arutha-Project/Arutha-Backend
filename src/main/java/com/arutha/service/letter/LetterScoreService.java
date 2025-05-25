package com.arutha.service.letter;

import com.arutha.model.letter.LetterScore;
import com.arutha.repository.letter.LetterScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterScoreService {

    private final LetterScoreRepository repository;

    public LetterScore saveScore(LetterScore score) {
        return repository.save(score);
    }

    public List<LetterScore> getAllScores() {
        return repository.findAll();
    }

    public List<LetterScore> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<LetterScore> getByLanguage(String language) {
        return repository.findByLanguage(language);
    }
}
