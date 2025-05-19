package com.arutha.api.response.ObjectIdentifier;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScoreResponse {
    private Integer id;               
    private Integer userId;           
    private String category;
    private Integer score;
    private Integer totalItems;
    private LocalDateTime createdAt;
}
