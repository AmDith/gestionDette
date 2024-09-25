package com.ism.entities;

import com.ism.enums.Etat;

import lombok.Data;

@Data

public class User {
  private int id;
  private String email;
  private String login;
  private String password;
  private Etat etat;
  private Role role;
  private Client client;
  private static int nbreU;


  public User() {
    this.id = ++nbreU;
  }

  public static void setNbreU(int n){
    User.nbreU = n;
}

public static int getNbreU() {
    return User.nbreU;
}

@Override
public String toString() {
    return "User{" +
            "id=" + id +
            ", email='" + email + 
            ", login='" + login + 
            ", password='" + password + 
            ", etat=" + etat +
            ", role=" + role +
            '}';
}

  
  
}
