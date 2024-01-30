package com.transacoes.transacoes.controllers;

import com.transacoes.transacoes.exceptions.DuplicatedEmail;
import com.transacoes.transacoes.exceptions.PersonNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviser {

  @ExceptionHandler(DuplicatedEmail.class)
  public ResponseEntity<String> handleEmailAlreadyThere(DuplicatedEmail e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já existente no banco de dados");
  }
  @ExceptionHandler(PersonNotFound.class)
  public ResponseEntity<String> handleFarmNotFound(PersonNotFound e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
  }

}
