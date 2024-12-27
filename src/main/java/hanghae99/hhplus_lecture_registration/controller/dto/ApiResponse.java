package hanghae99.hhplus_lecture_registration.controller.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiResponse {

    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
