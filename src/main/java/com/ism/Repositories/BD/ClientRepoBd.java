package com.ism.Repositories.BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ism.Core.Database.ClientRepoListInt;
import com.ism.Core.Database.UserRepoListInt;
import com.ism.Core.Database.Impl.RepositoryImplBd;
import com.ism.entities.Client;
import com.ism.entities.User;

public class ClientRepoBd extends RepositoryImplBd<Client> implements ClientRepoListInt {


    private UserRepoListInt uRepo;
    private Client client = null;


    public ClientRepoBd(UserRepoListInt uRepo) {
        this.uRepo = uRepo;
    }
    


// @Override
//   public int insert(Client amour) {
//       String query = generateSql("INSERT INTO `client` (`name`, `tel`, `adresse`, `idUser`) VALUES (?, ?, ?, ?)");
//       try (Connection conn = connexion();
//           PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

//           stmt.setString(1, amour.getName());
//           stmt.setString(2, amour.getTel());
//           stmt.setString(3, amour.getAdresse());
//             if (amour.getUser() != null) {
//                 stmt.setInt(4, uRepo.insert(amour.getUser()));
//             } else {
//                 stmt.setNull(4, java.sql.Types.INTEGER);
//             }

//             executeUpdate();
//       } catch (ClassNotFoundException e) {
//           System.out.println("Erreur de chargement du Driver: " + e.getMessage());
//       } catch (SQLException e) {
//           System.out.println("Erreur de Connexion a votre BD: " + e.getMessage());
//       }finally{
//         try {
//             closeConnection();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
//       return 0;
//   }


@Override
  public int insert(Client amour) {
      String query = generateSql("INSERT INTO `client` (`name`, `tel`, `adresse`, `idUser`) VALUES (?, ?, ?, ?)");
      try{
          conn = connexion();
          init(query);  
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




// @Override
// public List<Client> selectAll() {
//     List<Client> clients = new ArrayList<>();
//     String query =generateSql("SELECT c.* FROM client c");
    
//     try (Connection conn = connexion(); 
//          PreparedStatement stmt = conn.prepareStatement(query);
//          ResultSet rs = executeQuery()) {

//         while (rs.next()) {
//             client = new Client();
//             client = setfields(rs,client);
//             clients.add(client);
//         }

//     } catch (ClassNotFoundException | SQLException e) {
//         System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
//     }

//     return clients;
//  }

@Override
public List<Client> selectAll() {
    List<Client> clients = new ArrayList<>();
    String query =generateSql("SELECT c.* FROM client c");
    
    try{
        conn = connexion();  
        init(query);
        try (ResultSet rs = this.executeQuery()) {
            while (rs.next()) {
              client = new Client();
              client = setfields(rs,client);
              clients.add(client);
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

    return clients;
 }



//   @Override
//   public Client selectByPhone(String phone) {
//     String query = generateSql("SELECT c.* FROM client c " +
//     "WHERE c.tel = ?");
//     try (Connection conn = connexion(); 
//       PreparedStatement stmt = conn.prepareStatement(query)) {
//       stmt.setString(1, phone);
//       try (ResultSet rs = executeQuery()) {
//           if (rs.next()) {
//             client = new Client();
//             client = setfields(rs,client);
//             }
//       }
//     } catch (ClassNotFoundException | SQLException e) {
//     System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
//     }
//     return client;
//       }

@Override
  public Client selectByPhone(String phone) {
    String query = generateSql("SELECT c.* FROM client c " +
    "WHERE c.tel = ?");
    try{
      conn = connexion(); 
      init(query);
      stmt.setString(1, phone);
      try (ResultSet rs = this.executeQuery()) {
          if (rs.next()) {
            client = new Client();
            client = setfields(rs,client);
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
    return client;
      }


@Override
public void update(Client amour) {
    String query =generateSql( "UPDATE client SET idUser = ? WHERE id = ?");
      try{
        conn = connexion();  
          stmt.setInt(1, amour.getUser().getId());
          stmt.setInt(2, amour.getId());
          this.executeUpdate();
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
}







@Override
public Client setfields(ResultSet rs,Client client) throws SQLException {
    client.setId(rs.getInt("id"));
    client.setName(rs.getString("name"));
    client.setTel(rs.getString("tel"));
    client.setAdresse(rs.getString("adresse"));
    int idUser = rs.getInt("idUser");
    if (!rs.wasNull()) {
        if (client.getUser() == null) {
            client.setUser(new User());
        }
        client.getUser().setId(idUser);
    } else {
        client.setUser(null);
    }
    Client.setNbreC(Client.getNbreC() - 1);
    return client;

}


// @Override
// public Client selectBySurname(String val) {
//     String query = generateSql("SELECT c.* FROM client c " +
//     "WHERE c.name = ?");
//     try (Connection conn = connexion(); 
//     PreparedStatement stmt = conn.prepareStatement(query)) {
//       stmt.setString(1, val);
//       try (ResultSet rs = executeQuery()) {
//           if (rs.next()) {
//                 client = new Client();
//                 client = setfields(rs,client);
//           }
//       }
//     } catch (ClassNotFoundException | SQLException e) {
//     System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
//     }
//     return client;
// }


@Override
public Client selectBySurname(String val) {
    String query = generateSql("SELECT c.* FROM client c " +
    "WHERE c.name = ?");
    try{
        conn = connexion(); 
        init(query);
        stmt.setString(1, val);
        try (ResultSet rs = this.executeQuery()) {
            if (rs.next()) {
              client = new Client();
              client = setfields(rs,client);
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
    return client;
}



@Override
public int stmtfields(Client amour,String query) throws SQLException {
    try {
        init(query);
        stmt.setString(1, amour.getName());
        stmt.setString(2, amour.getTel());
        stmt.setString(3, amour.getAdresse());
        if (amour.getUser() != null) {
            stmt.setInt(4, uRepo.insert(amour.getUser()));
        } else {
            stmt.setNull(4, java.sql.Types.INTEGER);
        }
    
        return this.executeUpdate();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return 0;
}












  
}
