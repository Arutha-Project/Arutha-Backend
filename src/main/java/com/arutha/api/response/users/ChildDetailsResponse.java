package com.arutha.api.response.users;

import lombok.Getter;
import lombok.Setter;

/**
 * Response class for child details.
 */
@Getter
@Setter
public class ChildDetailsResponse {

    private Integer userId;
    private Integer studentId;
    private String studentName;

}
