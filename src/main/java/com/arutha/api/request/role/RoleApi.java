package com.arutha.api.request.role;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for Games.
 */
@Getter
@Setter
public class RoleApi {

    @NotNull
    private String roleName;

}
