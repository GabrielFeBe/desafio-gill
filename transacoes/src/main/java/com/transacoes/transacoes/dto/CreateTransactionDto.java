package com.transacoes.transacoes.dto;

import com.transacoes.transacoes.entities.TransactionEntity;
import java.util.Date;

public record CreateTransactionDto(Double value, Date transactiondate,Integer personid,String category,Integer id) {

  public TransactionEntity dtoToTransaction(){
    TransactionEntity transaction = new TransactionEntity();
    transaction.setId(id);
    transaction.setValue(value);
    transaction.setTransactiondate(transactiondate);
    transaction.setCategory(category);
    return transaction;
  }

}
