package com.transacoes.transacoes.controllers;

import com.transacoes.transacoes.dto.LoginDto;
import com.transacoes.transacoes.dto.PersonDto;
import com.transacoes.transacoes.dto.TransactionsReturnDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.exceptions.PersonNotFound;
import com.transacoes.transacoes.services.PersonService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
@CrossOrigin(origins = "*")
public class PersonController {

  @Autowired
  private PersonService personService;

  @PostMapping
  public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
    if (person.getId() == null) {
      var personRes = this.personService.createPerson(person);
      return ResponseEntity.status(200).body(personRes);
    } else {

      return ResponseEntity.status(500).build();
    }
  }

  @GetMapping
  public ResponseEntity<PersonDto> getPersonById(@PathParam("id") Integer id) {
    Optional<PersonEntity> person = this.personService.getPersonById(id);
    if (person.isEmpty()) {
      throw new PersonNotFound();
    }
    var confirmed = person.get().getTransactions();
    List<TransactionsReturnDto> transactionsReturnDto = confirmed.stream().map(transaction ->
        new TransactionsReturnDto(transaction.getId(), transaction.getValue(),
            transaction.getTransactiondate(), transaction.getCategory())).toList();
    return person.map(personEntity -> ResponseEntity.status(200)
            .body(new PersonDto(transactionsReturnDto, person.get().getEmail() , person.get().getName())))
        .orElseGet(() -> ResponseEntity.status(500).build());
  }

  @PostMapping("login")
  public ResponseEntity<Number> login(@RequestBody LoginDto login) {
    Integer Login = this.personService.loginPerson(login);
    return ResponseEntity.status(200).body(Login);
  }

}
