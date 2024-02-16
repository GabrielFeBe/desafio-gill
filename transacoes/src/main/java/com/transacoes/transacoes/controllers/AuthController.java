package com.transacoes.transacoes.controllers;

import com.transacoes.transacoes.dto.AuthDto;
import com.transacoes.transacoes.dto.LoginDto;
import com.transacoes.transacoes.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("login")
  @CrossOrigin("*")
  public AuthDto login(@RequestBody LoginDto login) {
    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
        login.username(), login.password());
    System.out.println(user);
    var auth = this.authenticationManager.authenticate(user);
    return new AuthDto(this.tokenService.generateToken(auth.getName()));
  }

}
