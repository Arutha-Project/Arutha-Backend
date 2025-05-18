package com.arutha.repository.numbers;

import com.arutha.model.number.NumberScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumberScoreRepository extends JpaRepository<NumberScore, Long> {
    List<NumberScore> findByUserId(Long userId);
}
