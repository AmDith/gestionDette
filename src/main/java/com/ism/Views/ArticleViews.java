package com.ism.Views;

import java.util.List;

import com.ism.Core.Database.ArticleRepoListInt;
import com.ism.Core.Database.Impl.ViewImpl;
import com.ism.Service.ArticleServiceInt;
import com.ism.entities.Article;
import com.ism.entities.Client;

import java.util.Scanner;

public class ArticleViews extends ViewImpl<Article,Client> implements ArticleViewInt{

  private ArticleServiceInt<Article,ArticleRepoListInt<Article>> articleService;

  public ArticleViews(Scanner scan,ArticleServiceInt<Article,ArticleRepoListInt<Article>> articleService) {
    ViewImpl.scan = scan;
    this.articleService = articleService;
  }
  @Override
  public Article created(Client mic) {
    Article michel = new Article();
   do {
      System.out.println("Veuillez saisir la reference");
      scan.nextLine();
      michel.setRef(scan.nextLine());
      System.out.println("Veuillez saisir le libelle de l'article");
      michel.setLibelle(scan.nextLine());
      System.out.println("Veuillez saisir le prix de l'article");
      michel.setPrix(scan.nextInt());
      System.out.println("Veuillez saisir la quantité de stock de l'article");
      michel.setQteStock(scan.nextInt());
   } while (articleService.findData().selectByLibelle(michel.getLibelle()) != null);
   return michel;
  }
  @ Override
    public void affiche(List<Article> datas){
      for (Article data : datas) {
        if (data.getQteStock() != 0) { 
          System.out.println(data.toString());
        }
      }
    }
    @Override
    public void Nonaffiche(List<Article> datas){
      for (Article data : datas) {
          System.out.println(data.toString());
      }
    }

    


  
  
}
