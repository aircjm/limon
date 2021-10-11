package com.aircjm.limon.security;

import lombok.Builder;
import lombok.Data;

/**
 * @author aircjm
 */
@Data
@Builder
public class UserLoginResponse {

    private String token;

}
