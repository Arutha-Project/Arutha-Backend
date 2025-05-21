package com.arutha.api.request.number;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * Request class for Number score save.
 */

@Data
public class SaveScoreRequest {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer score;
}
