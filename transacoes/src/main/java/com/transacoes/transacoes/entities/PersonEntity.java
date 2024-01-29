package com.transacoes.transacoes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "person")
@Setter
@Getter
public class PersonEntity {

  @Id
  @Column(name = "personid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String email;

  private String password;

  @OneToMany(mappedBy = "personid")
  @JsonIgnore
  private List<TransactionEntity> transactions;


}
