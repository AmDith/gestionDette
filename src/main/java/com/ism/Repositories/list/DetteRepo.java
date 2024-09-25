package com.ism.Repositories.list;


import com.ism.Core.Database.DetteRepoListInt;
import com.ism.Core.Database.Impl.RepositoryImpl;
import com.ism.entities.Dette;



public class DetteRepo extends RepositoryImpl<Dette> implements DetteRepoListInt {

  @Override
  public Dette selectById(int id) {
    return datas.stream()
                .filter(data -> data.getId() == id)
                .findFirst()
                .orElse(null);
  }

  
}
