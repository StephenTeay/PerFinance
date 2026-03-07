package com.teay.finance.config;

import com.teay.finance.services.JwtService;
import com.teay.finance.services.UsersDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsersDetailsService usersDetailsService;

    public JwtFilter(JwtService jwtService, UsersDetailsService usersDetailsService) {
        this.jwtService = jwtService;
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

       final String header = request.getHeader("Authorization");
       final String jwtToken;
       final String username;

       if(header == null || !header.startsWith("Bearer ")){
           filterChain.doFilter(request, response);
           return;
       }
       jwtToken = header.substring(7);
       username = jwtService.getUsername(jwtToken);
       if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails = this.usersDetailsService.loadUserByUsername(username);
       if(jwtService.isTokenValid(jwtToken, userDetails)){
           UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                   userDetails, null, userDetails.getAuthorities()
           );
           authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
           SecurityContextHolder.getContext().setAuthentication(authenticationToken);
       }
       }

filterChain.doFilter(request, response);

    }
}
