package com.cybersoft.cozastore_java21.filter;

import com.cybersoft.cozastore_java21.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// Tat ca cac request deu phai di vao Filter nay
@Component

public class JwtFilter extends OncePerRequestFilter {

    /**
     *
     *  Nhan duoc token truyen tren Header
     *  Giai ma token
     *  Neu giai ma thanh cong thi hop le
     *  Tao chung thuc va cho vao link
     *
     */

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Lay gia tri cua header co key la Authorization
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            // Cat bo chu Bearer va lay ra token
            String token = header.substring(7);
            // Giai ma token
            Claims claims = jwtHelper.decodeToken(token);

            if (claims != null) {
                // Tao chung thuc cho Security
                SecurityContext context = SecurityContextHolder.getContext();
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("","", new ArrayList<>());

                context.setAuthentication(user);

                System.out.println(user);
                System.out.println(token);
            }

        }

        filterChain.doFilter(request,response);
    }
}
