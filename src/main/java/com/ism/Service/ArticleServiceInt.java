package com.ism.Service;


import com.ism.Core.Database.Service;


public interface ArticleServiceInt<T,A> extends Service<T,A> {
  void updateQteStock(int qteRe,String val);
}
