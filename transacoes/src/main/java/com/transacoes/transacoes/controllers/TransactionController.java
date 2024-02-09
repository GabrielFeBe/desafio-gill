package com.transacoes.transacoes.controllers;

import com.transacoes.transacoes.dto.CreateTransactionDto;
import com.transacoes.transacoes.dto.TransactionsReturnDto;
import com.transacoes.transacoes.dto.ValueDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.entities.TransactionEntity;
import com.transacoes.transacoes.services.TransactionService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@CrossOrigin("*")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @GetMapping
  public ResponseEntity<TransactionEntity> getTransactionById(
      @AuthenticationPrincipal PersonEntity person) {
    TransactionEntity transaction = this.transactionService.getById(person.getId());
    return ResponseEntity.status(200).body(transaction);
  }

  @PutMapping
  public ResponseEntity updateTransaction(@AuthenticationPrincipal PersonEntity person,
      @RequestBody CreateTransactionDto transaction) {

    this.transactionService.updateTransaction(transaction.dtoToTransaction(), person);
    return ResponseEntity.status(200).build();
  }

  @PostMapping
  public ResponseEntity<TransactionEntity> createTransactions(
      @AuthenticationPrincipal PersonEntity person,
      @RequestBody CreateTransactionDto transaction) {
    TransactionEntity transactionRes = this.transactionService.createTransaction(
        transaction, person);
    return ResponseEntity.status(200).body(transactionRes);

  }

  @PostMapping("batch")
  public ResponseEntity<List<TransactionsReturnDto>> createTransactions(
      @RequestBody ValueDto<List<CreateTransactionDto>> listTransactios,
      @AuthenticationPrincipal PersonEntity person) {
    var listOfTransactionsCreated = this.transactionService.transactionBulkInsert(
        listTransactios.value(), person).stream().map(transaction -> new TransactionsReturnDto(
        transaction.getId(), transaction.getValue(), transaction.getTransactiondate(),
        transaction.getCategory())).toList();

    return ResponseEntity.status(200).body(listOfTransactionsCreated);
  }

  @GetMapping("category")
  public ResponseEntity<Double> getValueOfCategories(@AuthenticationPrincipal PersonEntity person,
      @PathParam("category") String category) {
    return ResponseEntity.status(200)
        .body(this.transactionService.getValues(person.getId(), category));
  }

  @DeleteMapping("all")
  public void deleteTransactionsByPersonId(@AuthenticationPrincipal PersonEntity person) {
    this.transactionService.deleteTransactionByPerson(person.getId());
  }

  @DeleteMapping
  public void deleteTransaction(@PathParam("id") Integer id) {
    this.transactionService.deleteTransactionById(id);
  }
}
