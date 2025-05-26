package com.arutha.repository.letter;

import com.arutha.model.letter.LetterScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Letter score repository.
 */

@Repository
public interface LetterScoreRepository extends JpaRepository<LetterScore, Long> {
    List<LetterScore> findByUserId(Long userId);

    List<LetterScore> findByLanguage(String language);

}
