package com.ism.entities;

import java.time.LocalDate;

import lombok.Data;

@Data

public class Paiement {
  private int id;
  private LocalDate date;
  private double montant;
  private Dette dette;
  private static int nbreP;

  public Paiement() {
    this.id = ++nbreP;
  }
  
}
