package com.ism.Views;

import java.util.List;

import com.ism.Core.Database.Views;
import com.ism.entities.Article;
import com.ism.entities.Client;

public interface ArticleViewInt extends Views<Article,Client>{
  void Nonaffiche(List<Article> datas);
  
}
