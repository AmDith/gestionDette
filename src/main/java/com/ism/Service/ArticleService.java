package com.ism.Service;

import java.util.List;

import com.ism.Core.Database.ArticleRepoListInt;
import com.ism.entities.Article;

import lombok.Data;


@Data

public class ArticleService implements ArticleServiceInt<Article,ArticleRepoListInt<Article>> {


  private ArticleRepoListInt<Article> articleRepo;

  
  
  public ArticleService(ArticleRepoListInt<Article> articleRepo) {
    this.articleRepo = articleRepo;
  }

  @Override
  public boolean saveList(Article objet) {
    if(objet != null){
      articleRepo.insert(objet);
      return true;
    }
    return false;
  }

  @Override
  public List<Article> show() {
    return articleRepo.selectAll();
  }


  @Override
  public void updateQteStock(int qteRe,String val) {
    for (Article article : articleRepo.selectAll()) {
      if(article.getLibelle().compareTo(val)==0){
        article.setQteStock(article.getQteStock() - qteRe);
      } 
    }
  }


  @Override
  public ArticleRepoListInt<Article> findData() {
    return articleRepo;
  }

  
}
