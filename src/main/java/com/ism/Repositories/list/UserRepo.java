package com.ism.Repositories.list;


import com.ism.Core.Database.UserRepoListInt;
import com.ism.Core.Database.Impl.RepositoryImpl;
import com.ism.entities.User;



public class UserRepo extends RepositoryImpl<User> implements UserRepoListInt {

  @Override
  public User selectByLogin(String val) {
    return datas.stream()
    .filter(user -> user.getLogin().compareTo(val) == 0)
    .findFirst()
    .orElse(null);
  }

  @Override
public String login(String val) {
  boolean isUnique = datas.stream()
  .noneMatch(user -> user.getLogin().equals(val));

      return isUnique ? val : null;
  }

  

  

  
}
