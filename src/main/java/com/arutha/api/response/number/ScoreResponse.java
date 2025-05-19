package com.arutha.api.response.number;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Response class for NumberScore.
 */
@Setter
@Getter
@Builder
public class ScoreResponse {
    private Integer id;
    private Integer userId;
    private Integer score;
    private LocalDateTime createAt;
}
