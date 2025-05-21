package com.arutha.api.request.objectidentifier;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request class for Object Identification.
 */
@Data
public class ObjectIdentifierScoreRequest {

    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Score cannot be null")
    @Min(value = 0, message = "Score cannot be negative")
    private Integer score;

    @NotNull(message = "Total items cannot be null")
    @Min(value = 0, message = "Total items cannot be negative")
    private Integer totalItems;
}
