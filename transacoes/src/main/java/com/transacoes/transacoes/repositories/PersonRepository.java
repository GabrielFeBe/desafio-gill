package com.transacoes.transacoes.repositories;

import com.transacoes.transacoes.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Integer> {

}
