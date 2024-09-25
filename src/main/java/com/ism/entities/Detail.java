package com.ism.entities;

import lombok.Data;

@Data

public class Detail {
  private int id;
  private int qte;
  private Dette dette;
  private Article article;
  private static int nbreDE;

  public Detail() {
    this.id = ++nbreDE;
  }
  
}
