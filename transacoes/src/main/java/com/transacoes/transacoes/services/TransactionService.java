package com.transacoes.transacoes.services;

import com.transacoes.transacoes.dto.CreateTransactionDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.entities.TransactionEntity;
import com.transacoes.transacoes.exceptions.PersonNotFound;
import com.transacoes.transacoes.exceptions.TransactionNotFound;
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

  public TransactionEntity createTransaction(CreateTransactionDto transaction,
      PersonEntity person) {
    TransactionEntity transactionEntity = transaction.dtoToTransaction();

    transactionEntity.setPersonid(person);
    return this.transactionRepository.save(transactionEntity);
  }

  public void updateTransaction(TransactionEntity transaction, PersonEntity person) {

    transaction.setPersonid(person);
    this.transactionRepository.save(transaction);
  }

  public List<TransactionEntity> transactionBulkInsert(List<CreateTransactionDto> transactions,
      PersonEntity person) {
    return transactions.stream().map(transaction ->
        this.createTransaction(transaction, person)
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

  public TransactionEntity getById(Integer id) {
    Optional<TransactionEntity> transaction = this.transactionRepository.findById(id);
    if (transaction.isEmpty()) {
      throw new TransactionNotFound();
    }
    return transaction.get();
  }
}
