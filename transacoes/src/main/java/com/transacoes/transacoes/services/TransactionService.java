package com.transacoes.transacoes.services;

import com.transacoes.transacoes.dto.CreateTransactionDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.entities.TransactionEntity;
import com.transacoes.transacoes.exceptions.PersonNotFound;
import com.transacoes.transacoes.repositories.PersonRepository;
import com.transacoes.transacoes.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
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

    Optional<PersonEntity> person = this.personRepository.findById(transaction.personid());
    if (person.isEmpty()) {
      throw new PersonNotFound();
    }

    transactionEntity.setPersonid(person.get());
    return this.transactionRepository.save(transactionEntity);
  }

  public void updateTransaction(TransactionEntity transaction, Integer id) {

    Optional<PersonEntity> person = this.personRepository.findById(id);
    if (person.isEmpty()) {
      throw new PersonNotFound();
    }
    transaction.setPersonid(person.get());
    this.transactionRepository.save(transaction);
  }

  public List<TransactionEntity> transactionBulkInsert(List<CreateTransactionDto> transactions) {
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
    List<TransactionEntity> transactions = this.transactionRepository.findByPersonid_IdAndCategory(
        personid, category);
    Double value = 0.00D;
    for (TransactionEntity transaction : transactions
    ) {
      value += transaction.getValue();
    }
    return value;
  }
}
