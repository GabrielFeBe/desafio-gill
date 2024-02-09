package com.transacoes.transacoes.repositories;

import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.entities.TransactionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

  Optional<PersonEntity> findByUsername(String username);
}
