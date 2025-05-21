package com.arutha.service.objectidentifier;

import com.arutha.api.request.objectidentifier.ObjectIdentifierScoreRequest;
import com.arutha.api.response.objectidentifier.ScoreResponse;
import com.arutha.model.objectidentifier.ObjectIdentifierScore;
import com.arutha.model.users.Users;
import com.arutha.repository.objectidentifier.ObjectIdentifierScoreRepository;
import com.arutha.repository.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing ObjectIdentifierScore entities.
 * This class contains methods to save scores and retrieve scores by user ID.
 */

@AllArgsConstructor
@Service
public class ObjectIdentifierScoreService {

    private final ObjectIdentifierScoreRepository scoreRepository;

    private final UserRepository userRepository;

    /**
     * Saves the score for a user.
     *
     * @param request The request object containing score details.
     */

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

    /**
     * Retrieves scores for a specific user by their ID.
     *
     * @param userId The ID of the user whose scores are to be retrieved.
     * @return A list of ScoreResponse objects containing score details.
     */

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
