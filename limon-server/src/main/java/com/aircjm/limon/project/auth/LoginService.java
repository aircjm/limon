package com.aircjm.limon.project.auth;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;

/**
 * @author aircjm
 */
@Service
public class LoginService {


    public LoginResponse login(LoginRequest request) {
        return new LoginResponse(request.getUsername(), IdUtil.fastUUID());
    }
}
