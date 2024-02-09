package com.transacoes.transacoes.services;

import com.transacoes.transacoes.dto.LoginDto;
import com.transacoes.transacoes.entities.PersonEntity;
import com.transacoes.transacoes.exceptions.DuplicatedEmail;
import com.transacoes.transacoes.exceptions.PersonNotFound;
import com.transacoes.transacoes.repositories.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements UserDetailsService {

  @Autowired
  private PersonRepository personRepository;

  public PersonEntity createPerson(PersonEntity person) {
    Optional<PersonEntity> isAlreadyUsed = this.personRepository.findByUsername(
        person.getUsername());
    if (isAlreadyUsed.isPresent()) {
      throw new DuplicatedEmail();
    }
    person.setPassword(new BCryptPasswordEncoder()
        .encode(person.getPassword()));
    return this.personRepository.save(person);
  }


  public void deletePerson(Integer id) {
    this.personRepository.deleteById(id);
  }

  public Optional<PersonEntity> getPersonById(Integer id) {
    return this.personRepository.findById(id);
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.personRepository.findByUsername(username).orElseThrow(PersonNotFound::new);
  }
}
