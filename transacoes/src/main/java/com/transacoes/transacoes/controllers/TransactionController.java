package com.transacoes.transacoes.controllers;

import com.transacoes.transacoes.dto.CreateTransactionDto;
import com.transacoes.transacoes.dto.TransactionsReturnDto;
import com.transacoes.transacoes.dto.ValueDto;
import com.transacoes.transacoes.entities.TransactionEntity;
import com.transacoes.transacoes.services.TransactionService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<TransactionEntity> getTransactionById(@PathParam("id") Integer id) {
    TransactionEntity transaction = this.transactionService.getById(id);
    return ResponseEntity.status(200).body(transaction);
  }

  @PutMapping
  public ResponseEntity updateTransaction(@PathParam("id") Integer id,
      @RequestBody CreateTransactionDto transaction) {

    this.transactionService.updateTransaction(transaction.dtoToTransaction(), id);
    return ResponseEntity.status(200).build();
  }

  @PostMapping
  public ResponseEntity<TransactionEntity> createTransactions(
      @RequestBody CreateTransactionDto transaction) {
    TransactionEntity transactionRes = this.transactionService.createTransaction(
        transaction);
    return ResponseEntity.status(200).body(transactionRes);

  }

  @PostMapping("batch")
  public ResponseEntity<List<TransactionsReturnDto>> createTransactions(
      @RequestBody ValueDto<List<CreateTransactionDto>> listTransactios) {
    var listOfTransactionsCreated = this.transactionService.transactionBulkInsert(
        listTransactios.value()).stream().map(transaction -> new TransactionsReturnDto(
        transaction.getId(), transaction.getValue(), transaction.getTransactiondate(),
        transaction.getCategory())).toList();

    return ResponseEntity.status(200).body(listOfTransactionsCreated);
  }

  @GetMapping("category")
  public ResponseEntity<Double> getValueOfCategories(@PathParam("id") Integer id,
      @PathParam("category") String category) {
    return ResponseEntity.status(200).body(this.transactionService.getValues(id, category));
  }

  @DeleteMapping("all")
  public void deleteTransactionsByPersonId(@PathParam("personid") Integer personid) {
    System.out.println(personid);
    this.transactionService.deleteTransactionByPerson(personid);
  }

  @DeleteMapping
  public void deleteTransaction(@PathParam("id") Integer id) {
    this.transactionService.deleteTransactionById(id);
  }
}
