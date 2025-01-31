package com.travelportfolio.TPUSA.configuration;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.travelportfolio.TPUSA.service.JWTService;
import com.travelportfolio.TPUSA.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Custom filter we want to make it behave like OncePerRequestFilter
@Component
public class JWTFilter extends OncePerRequestFilter {
  @Autowired
  JWTService jwtService;
  @Autowired
  ApplicationContext applicationContext;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // We will get "Bearer + token contetnt(Start content from & byte)"
    String authHead = request.getHeader("Authorization");
    String token = null;
    String username = null;
    try {
      if (authHead != null && authHead.startsWith("Bearer ")) {
        token = authHead.substring(7);
        username = jwtService.extractUserName(token);
      }
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        // System.out.println("In authentication username is " + username);
        UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(username);
        // System.out.println("In authentication token is " + token);
        // System.out.println("In Validate token bool " +
        // jwtService.validateToken(token, userDetails));
        if (jwtService.validateToken(token, userDetails)) {

          // If token is valid then we will set the authentication in the context
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
          System.out.println("Token is not valid");
        }
      }
      filterChain.doFilter(request, response);

    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json");
      response.getWriter().write(
          "{\"error\": \"Invalid JWT signature or Token is expires Login again to generate token. Please provide a valid token.\"}");
      response.getWriter().flush();
    }

  }

}
