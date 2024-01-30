package com.transacoes.transacoes.services;

import com.transacoes.transacoes.dto.LoginDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.exceptions.DuplicatedEmail;
import com.transacoes.transacoes.exceptions.PersonNotFound;
import com.transacoes.transacoes.repositories.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public PersonEntity createPerson(PersonEntity person) {
    Optional<PersonEntity> isAlreadyUsed = this.personRepository.findByEmail(person.getEmail());
    if(isAlreadyUsed.isPresent()) throw new DuplicatedEmail();
    return this.personRepository.save(person);
  }


  public void deletePerson(Integer id) {
    this.personRepository.deleteById(id);
  }

  public Optional<PersonEntity> getPersonById(Integer id) {
    return this.personRepository.findById(id);
  }

  public Integer loginPerson(LoginDto login) {
    Optional<PersonEntity> person = this.personRepository.findByEmailAndPassword(login.email(), login.password());
    if(person.isEmpty()) throw new PersonNotFound();
    return person.get().getId();

  }
}
