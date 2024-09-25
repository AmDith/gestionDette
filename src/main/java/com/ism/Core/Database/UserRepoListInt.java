package com.ism.Core.Database;

import com.ism.entities.User;

public interface UserRepoListInt extends Repository<User> {
  String login(String val);
  User selectByLogin(String val);
}
