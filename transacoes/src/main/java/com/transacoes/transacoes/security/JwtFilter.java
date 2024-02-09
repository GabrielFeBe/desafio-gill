package com.transacoes.transacoes.security;

import com.transacoes.transacoes.services.PersonService;
import com.transacoes.transacoes.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final PersonService personService;

  @Autowired
  public JwtFilter(TokenService tokenService, PersonService personService) {
    this.tokenService = tokenService;
    this.personService = personService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    Optional<String> token = extractToken(request);
    if (token.isPresent()) {
      String subject = this.tokenService.validateToken(token.get());
      UserDetails user = this.personService.loadUserByUsername(subject);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  public Optional<String> extractToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header == null) {
      return Optional.empty();
    }
    return Optional.of(
        header.replace("Bearer ", "")
    );

  }

}
