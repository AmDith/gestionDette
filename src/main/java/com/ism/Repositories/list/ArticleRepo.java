package com.ism.Repositories.list;


import com.ism.Core.Database.ArticleRepoListInt;
import com.ism.Core.Database.Impl.RepositoryImpl;
import com.ism.entities.Article;



public class ArticleRepo extends RepositoryImpl<Article> implements ArticleRepoListInt<Article> {

  @Override
  public Article selectByLibelle(String val) {
    return datas.stream()
    .filter(article -> article.getLibelle().compareTo(val) == 0)
    .findFirst()
    .orElse(null);
  }

  

  
  
}
