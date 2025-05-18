package com.arutha.service.number;

import com.arutha.model.number.NumberScore;
import com.arutha.model.users.Users;
import com.arutha.repository.numbers.NumberScoreRepository;
import com.arutha.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NumberScoreService {
    private final NumberScoreRepository numberScoreRepository;
    private final UserRepository userRepository;

    public NumberScore saveScore(Integer userId, Integer score) {
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        NumberScore numberScore = NumberScore.builder()
                .user(users)
                .score(score)
                .createdAt(LocalDateTime.now())
                .build();

        return numberScoreRepository.save(numberScore);
    }

    public List<NumberScore> getUserScores(Long userId) {
        return numberScoreRepository.findByUserId(userId);
    }
}
