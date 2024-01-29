package com.transacoes.transacoes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "transactions")
@Setter
@Getter
public class TransactionEntity {
  @Id
  @Column(name = "transactionId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Double value;

  private Date transactiondate;

  @ManyToOne
  @JoinColumn( name = "personid" )
  private PersonEntity personid;

  private String category;

}
