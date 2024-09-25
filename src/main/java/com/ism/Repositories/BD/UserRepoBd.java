package com.ism.Repositories.BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ism.Core.Database.UserRepoListInt;
import com.ism.Core.Database.Impl.RepositoryImplBd;
import com.ism.entities.Role;
import com.ism.entities.User;
import com.ism.enums.Etat;

public class UserRepoBd extends RepositoryImplBd<User>  implements UserRepoListInt{

    private List<User> users = new ArrayList<>();
    private User user = null;

  @Override
  public int insert(User amour) {
    String query = generateSql("INSERT INTO `user` (`email`, `login`, `password`, `etatId`, `roleId`) VALUES (?, ?, ?, ?, ?)");
      try {
          conn = connexion();
          return stmtfields(amour,query);
      } catch (ClassNotFoundException e) {
          System.out.println("Erreur de chargement du Driver: " + e.getMessage());
      } catch (SQLException e) {
          System.out.println("Erreur de Connexion a votre BD: " + e.getMessage());
      }finally{
        try {
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      return 0;
  }

  @Override
  public List<User> selectAll() {
        String query = generateSql("SELECT u.*, e.nomEtat, r.nomRole " +
                        "FROM user u " +
                        "JOIN etat e ON u.etatId = e.id " +
                        "JOIN role r ON u.roleId = r.id");

        try {
            conn = connexion();  
            init(query);
            try (ResultSet rs = this.executeQuery()) {
                while (rs.next()) {
                    user = new User();
                    user = setfields(rs,user);
                    users.add(user);
                }
          }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }
        return users;
  }

  @Override
  public User selectByLogin(String login) {
    String query = generateSql("SELECT u.*, e.nomEtat, r.nomRole " +
                    "FROM user u " +
                    "JOIN etat e ON u.etatId = e.id " +
                    "JOIN role r ON u.roleId = r.id "+
                    "WHERE u.login = ? ");
    try{
        conn = connexion(); 
        init(query);
        stmt.setString(1, login);
        try (ResultSet rs = this.executeQuery()) {
          if (rs.next()) {
                user = new User();
                user = setfields(rs,user);
          }
      }
    } catch (ClassNotFoundException | SQLException e) {
    System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
    }finally{
        try {
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return user;
    }



@Override
public User setfields(ResultSet rs,User user)  throws SQLException {
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setEtat(Etat.valueOf(rs.getString("nomEtat")));
        Role role = new Role();
        String nomRole = rs.getString("nomRole");
        if (nomRole.equals("Admin")) {
            role.setId(1);
        } else if (nomRole.equals("Boutiquier")) {
            role.setId(2);}
        else if (nomRole.equals("Client")) {
                role.setId(3);}    
        role.setNomRole(rs.getString("nomRole"));
        user.setRole(role);
        User.setNbreU(User.getNbreU()-1);
    return user;

}

@Override
public String login(String val) {
    users = selectAll();
    boolean isUnique = users.stream()
                            .noneMatch(user -> user.getLogin().equals(val));
    
    return isUnique ? val : null;
}

@Override
public int stmtfields(User amour,String query) throws SQLException {
    try {
        init(query);
        stmt.setString(1, amour.getEmail());
        stmt.setString(2, amour.getLogin());
        stmt.setString(3, amour.getPassword());
        int etatId = amour.getEtat() == Etat.Activer ? 1 : 2;
        stmt.setInt(4, etatId);
        stmt.setInt(5, amour.getRole().getId()); 
    
        this.executeUpdate();
    
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return 0;
}


}








    












  

