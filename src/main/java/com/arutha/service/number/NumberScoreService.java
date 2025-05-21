package com.arutha.service.number;

import com.arutha.api.request.number.SaveScoreRequest;
import com.arutha.api.response.number.ScoreResponse;
import com.arutha.model.number.NumberScore;
import com.arutha.model.users.Users;
import com.arutha.repository.number.NumberScoreRepository;
import com.arutha.repository.users.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Number score service.
 */
@Service
@AllArgsConstructor
public class NumberScoreService {

    /**
     * Number score service methods.
     */
    private final NumberScoreRepository numberScoreRepository;
    private final UserRepository userRepository;

    /**
     * Save score method.
     */
    public NumberScore saveScore(SaveScoreRequest request) {
        NumberScore numberScore = new NumberScore();

        Users users = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        numberScore.setUser(users);
        numberScore.setScore(request.getScore());

        return numberScoreRepository.save(numberScore);
    }

    /**
     *get User Scores.
     */
    public List<ScoreResponse> getUserScores(Integer userId) {
        List<NumberScore> numberScores = numberScoreRepository.findByUserId(userId);

        return numberScores.stream()
                .map(numberScore -> {
                    ScoreResponse response = new ScoreResponse();
                    response.setId(numberScore.getId());
                    response.setUserId(numberScore.getUser().getId());
                    response.setScore(numberScore.getScore());
                    return response;
                })
                .toList();
    }
}
