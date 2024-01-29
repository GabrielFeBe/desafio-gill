package com.transacoes.transacoes.repositories;

import com.transacoes.transacoes.entities.TransactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Integer> {

List<TransactionEntity> findByPersonid_IdAndCategory(Integer personid, String category);
List<TransactionEntity> findByPersonid_Id(Integer personid);
void deleteByPersonid_Id(Integer personid);
}
