package com.aircjm.limon.project.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aircjm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    @Schema(name = "username")
    private String username;

    @Schema(name = "token")
    private String token;

}
