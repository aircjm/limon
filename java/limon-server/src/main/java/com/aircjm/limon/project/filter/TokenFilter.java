package com.aircjm.limon.project.filter;


import cn.hutool.core.util.StrUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * @author aircjm
 */
@Component
public class TokenFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");
//        if ("OPTIONS".equals(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                throw new ServletException("Missing or invalid Authorization header");
//            }
//            final String token = authHeader.substring(7);
//            final Claims claims = Jwts.parser()
//                    .setSigningKey("secretkey")
//                    .parseClaimsJws(token)
//                    .getBody();
//            request.setAttribute("claims", claims);
//            filterChain.doFilter(req, res);
//        }

        if (StrUtil.isBlank(authHeader)) {
            throw new ServletException("Missing or invalid Authorization header");
        }


        filterChain.doFilter(req, res);

    }
}
