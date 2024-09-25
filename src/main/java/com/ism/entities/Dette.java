package com.ism.entities;

import java.time.LocalDate;

import com.ism.enums.EtatDette;

import lombok.Data;

@Data

public class Dette {
  private int id;
  private LocalDate date;
  private double montant;
  private double montantVerser;
  private double montantRestant;
  private EtatDette etat;
  private static int nbreD;
  

  public Dette() {
    this.id = ++nbreD;
  }

}
