package com.arutha.service.ObjectIdentifier;

import com.arutha.api.request.ObjectIdentifier.ObjectIdentifierScoreRequest;
import com.arutha.api.response.ObjectIdentifier.ScoreResponse;
import com.arutha.model.ObjectIdentifier.ObjectIdentifierScore;
import com.arutha.model.users.Users;
import com.arutha.repository.ObjectIdentifier.ObjectIdentifierScoreRepository;
import com.arutha.repository.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ObjectIdentifierScoreService {

    private final ObjectIdentifierScoreRepository scoreRepository;

    private final UserRepository userRepository;

    public void saveScore(ObjectIdentifierScoreRequest request) {
        ObjectIdentifierScore score = new ObjectIdentifierScore();

        // Assuming user exists — you can add error handling
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        score.setUser(user);
        score.setCategory(request.getCategory());
        score.setScore(request.getScore());
        score.setTotalItems(request.getTotalItems());

        scoreRepository.save(score);
    }

    public List<ScoreResponse> getScoresByUserId(Integer userId) {
        List<ObjectIdentifierScore> objectIdentifierScores = scoreRepository.findByUserId(userId);

        return objectIdentifierScores.stream()
                .map(score -> {
                    ScoreResponse response = new ScoreResponse();
                    response.setId(score.getId());
                    response.setUserId(score.getUser().getId());
                    response.setCategory(score.getCategory());
                    response.setScore(score.getScore());
                    response.setTotalItems(score.getTotalItems());
                    response.setCreatedAt(score.getCreatedAt());
                    return response;
                })
                .toList();
    }
}
