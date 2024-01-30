package com.transacoes.transacoes.services;

import com.transacoes.transacoes.dto.CreateTransactionDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.entities.TransactionEntity;
import com.transacoes.transacoes.repositories.PersonRepository;
import com.transacoes.transacoes.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private PersonRepository personRepository;

  public TransactionEntity createTransaction(CreateTransactionDto transaction) {
    TransactionEntity transactionEntity = transaction.dtoToTransaction();

    PersonEntity person = this.personRepository.findById(transaction.personid()).orElseThrow();
    transactionEntity.setPersonid(person);
    return this.transactionRepository.save(transactionEntity);
  }
public void updateTransaction(TransactionEntity transaction , Integer id) {

  PersonEntity person = this.personRepository.findById(id).orElseThrow();
  TransactionEntity transactionEntity = transaction;
  transactionEntity.setPersonid(person);
  this.transactionRepository.save(transactionEntity);
}

  public List<TransactionEntity> transactionBulkInsert(List<CreateTransactionDto> transactions){
    return transactions.stream().map(this::createTransaction
    ).toList();
  }

  public void deleteTransactionById(Integer id) {
    this.transactionRepository.deleteById(id);
  }

  @Transactional
  public void deleteTransactionByPerson(Integer id) {
    this.transactionRepository.deleteByPersonid_Id(id);
  }

  public List<TransactionEntity> findTransactionByPersonAndCategory(Integer personid,
      String category) {
    return this.transactionRepository.findByPersonid_IdAndCategory(personid, category);
  }

  public List<TransactionEntity> findTransactionByPerson(Integer personid) {
    return this.transactionRepository.findByPersonid_Id(personid);
  }

  public Double getValues(Integer personid, String category) {
    List<TransactionEntity> transactions = this.transactionRepository.findByPersonid_IdAndCategory(4,category);
    Double value = 0.00D;
    for (TransactionEntity transaction: transactions
    ) {
      value += transaction.getValue();
    }
    return value;
  }
}
