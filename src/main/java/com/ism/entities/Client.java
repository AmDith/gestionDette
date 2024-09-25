package com.ism.entities;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true)

public class Client {
  @ToString.Include
  private int id;
  @ToString.Include
  private String name;
  @ToString.Include
  private String tel;
  @ToString.Include
  private String adresse;
  private User user;
  private List<Dette> dettes;
  private static int nbreC;

  public Client() {
    this.id = ++nbreC;
  }
  
public static void setNbreC(int n){
  Client.nbreC = n;
}

public static int getNbreC() {
  return Client.nbreC;
}


  

}
