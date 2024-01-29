package com.transacoes.transacoes.services;

import com.transacoes.transacoes.dto.CreateTransactionDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.entities.TransactionEntity;
import com.transacoes.transacoes.repositories.PersonRepository;
import com.transacoes.transacoes.repositories.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private PersonRepository personRepository;

  public TransactionEntity createAndUpdateTransaction(CreateTransactionDto transaction) {
    TransactionEntity transactionEntity = transaction.dtoToTransaction();

    PersonEntity person = this.personRepository.findById(transaction.personid()).orElseThrow();
    transactionEntity.setPersonid(person);
    return this.transactionRepository.save(transactionEntity);
  }

  public List<TransactionEntity> transactionBulkInsert(List<CreateTransactionDto> transactions){
    return transactions.stream().map(this::createAndUpdateTransaction
    ).toList();
  }

  public void deleteTransactionById(Integer id) {
    this.transactionRepository.deleteById(id);
  }

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

}
