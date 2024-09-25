package com.ism.entities;



import lombok.Data;

@Data

public class Article {
  private int id;
  private String ref;
  private String libelle;
  private double prix;
  private int qteStock;
  private static int nbreA;
  
  public Article() {
    this.id = ++nbreA;
  }
  
  
}
