package com.ism.Factory.FactoryService;

import com.ism.Core.Database.ArticleRepoListInt;
import com.ism.Core.Database.ClientRepoListInt;
import com.ism.Core.Database.DetteRepoListInt;
import com.ism.Core.Database.UserRepoListInt;
import com.ism.Service.ArticleService;
import com.ism.Service.ClientService;
import com.ism.Service.DetteService;
import com.ism.Service.UserService;
import com.ism.entities.Article;

public class FactoryService {
  private FactoryService(){

  }

  private static ArticleService articleService = null;
  private static ClientService clientService = null ;
  private static DetteService detteService = null;
  private static UserService userService = null ;


  public static ArticleService getInstanceA(ArticleRepoListInt<Article> breuhk) {
    if (articleService == null) {
      articleService = new ArticleService(breuhk);
      }
      return articleService;
}


public static ClientService getInstanceC(ClientRepoListInt breuhk) {
  if (clientService == null) {
    clientService = new ClientService(breuhk);
    }
    return clientService;
}

public static DetteService getInstanceD(DetteRepoListInt breuhk) {
  if (detteService == null) {
    detteService = new DetteService(breuhk);
    }
    return detteService;
}


public static UserService getInstanceU(UserRepoListInt breuhk) {
  if (userService == null) {
    userService = new UserService(breuhk);
    }
    return userService;
}

}
