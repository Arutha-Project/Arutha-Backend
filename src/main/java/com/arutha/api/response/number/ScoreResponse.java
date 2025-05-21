package com.arutha.api.response.number;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Response class for NumberScore.
 */
@Data
public class ScoreResponse {
    private Integer id;
    private Integer userId;
    private Integer score;
    private LocalDateTime createAt;
}
