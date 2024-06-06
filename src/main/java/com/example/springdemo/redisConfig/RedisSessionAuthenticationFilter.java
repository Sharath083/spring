package com.example.springdemo.redisConfig;

import com.example.springdemo.contants.Constants;
import com.example.springdemo.helper.Crypto;
import com.example.springdemo.jwt.JwtRequest;
import com.example.springdemo.service.CustomStudentService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RedisSessionAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private Crypto crypto;

    @Autowired
    private CustomStudentService userService;

    private static JwtRequest jwtRequest = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String sessionId = request.getHeader("session-id");

        if (sessionId != null) {
            try {

                UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserId());
                jwtRequest = validateSession(sessionId,userDetails);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private JwtRequest validateSession(String session,UserDetails userDetails) throws AuthenticationException {
        try {
            String[] data = crypto.decrypt(session).split("//");
            String key = Constants.REDIS_KEY + data[0];
            String value = redisHelper.get(key);
            if (value == null) {
                throw new AuthenticationException("Session Expired") {};
            } else if (value.equals(session) && data[1].equals(userDetails.getUsername())) {
                return new JwtRequest(data[0], data[1]);
            }else {
                throw new AuthenticationException("Invalid session key") {
                };
            }
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("Invalid session key") {};
        }
    }

    public static JwtRequest getUserData() {
        return jwtRequest;
    }
}
