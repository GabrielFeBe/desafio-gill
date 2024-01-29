package com.transacoes.transacoes.controllers;

import com.transacoes.transacoes.dto.CreateTransactionDto;
import com.transacoes.transacoes.dto.TransactionsReturnDto;
import com.transacoes.transacoes.dto.ValueDto;
import com.transacoes.transacoes.entities.TransactionEntity;
import com.transacoes.transacoes.services.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TransactionEntity> createTransactions(
      @RequestBody CreateTransactionDto transaction) {
    TransactionEntity transactionRes = this.transactionService.createAndUpdateTransaction(
        transaction);
    return ResponseEntity.status(200).body(transactionRes);

  }

  @PostMapping("batch")
  public ResponseEntity<List<TransactionsReturnDto>> createTransactions(
      @RequestBody ValueDto<List<CreateTransactionDto>> listTransactios) {

    var listOfTransactionsCreated = this.transactionService.transactionBulkInsert(
        listTransactios.value()).stream().map(transaction -> new TransactionsReturnDto(
        transaction.getId(),transaction.getValue(), transaction.getTransactiondate(),transaction.getCategory() )).toList();

    return ResponseEntity.status(200).body(listOfTransactionsCreated);
  }


}
