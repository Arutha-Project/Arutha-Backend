package com.arutha.api.response.number;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Resepon class for User Registration.
 */
@Setter
@Getter
@Builder
public class ScoreResponse {
    private Integer id;
    private Integer user_id;
    private Integer score;
    private LocalDateTime create_at;
}
