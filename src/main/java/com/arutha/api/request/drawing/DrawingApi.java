package com.arutha.api.request.drawing;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for Drawing.
 */
@Getter
@Setter
public class DrawingApi {

    @NotNull
    private Integer score;

    @NotNull
    private Integer total;

    @NotNull
    private Integer userId;
}
