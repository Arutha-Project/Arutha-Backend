package com.arutha.repository.number;

import com.arutha.model.number.NumberScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Number score repository.
 */
@Repository
public interface NumberScoreRepository extends JpaRepository<NumberScore, Integer> {
    List<NumberScore> findByUserId(Integer userId);
}
