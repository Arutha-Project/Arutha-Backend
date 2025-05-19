package com.arutha.api.request.number;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for Number score save.
 */

@Getter
@Setter
public class SaveScoreRequest {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer score;
}
