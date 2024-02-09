package com.transacoes.transacoes.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private final Algorithm algorithm;


  public TokenService() {
    algorithm = Algorithm.HMAC256("segredopae");
  }

  public String validateToken(String token) {
    return JWT.require(algorithm).build().verify(token).getSubject();
  }

  public String generateToken(String username) {
    return JWT.create().withSubject(username).withExpiresAt(this.generateExpiration())
        .sign(algorithm);
  }

  private Instant generateExpiration() {
    return Instant.now().plus(24, ChronoUnit.HOURS);
  }

}
