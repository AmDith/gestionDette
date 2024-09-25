package com.ism.Repositories.list;



import com.ism.Core.Database.ClientRepoListInt;
import com.ism.Core.Database.Impl.RepositoryImpl;
import com.ism.entities.Client;


public class ClientRepo extends RepositoryImpl<Client> implements ClientRepoListInt {


  @Override
  public Client selectByPhone(String phone) {
    return datas.stream()
    .filter(client -> client.getTel().compareTo(phone) == 0)
    .findFirst()
    .orElse(null);
}

  @Override
  public void update(Client amour) {
  }

  @Override
  public Client selectBySurname(String val) {
    return datas.stream()
    .filter(client -> client.getName().compareTo(val) == 0)
    .findFirst()
    .orElse(null);
  }
  
}
