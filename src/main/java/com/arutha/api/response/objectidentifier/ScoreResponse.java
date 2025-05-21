package com.arutha.api.response.objectidentifier;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Response class for Object Identifier score.
 */

@Data

public class ScoreResponse {
    private Integer id;               
    private Integer userId;           
    private String category;
    private Integer score;
    private Integer totalItems;
    private LocalDateTime createdAt;
}
