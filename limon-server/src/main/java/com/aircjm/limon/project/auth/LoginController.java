package com.aircjm.limon.project.auth;

import com.aircjm.limon.system.vo.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aircjm
 */
@RestController
public class LoginController {


    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    private Result<LoginResponse> login(LoginRequest request) {
        LoginResponse response = loginService.login(request);
        return Result.success(response);
    }

}
