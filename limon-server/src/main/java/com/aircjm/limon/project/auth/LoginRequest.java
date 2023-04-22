package com.aircjm.limon.project.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author aircjm
 */
@Data
public class LoginRequest {

    @Schema(name = "username")
    private String username;
    @Schema(name = "password")
    private String password;


}
