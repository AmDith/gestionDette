package com.ism.Core.Database;

public interface ArticleRepoListInt<T> extends Repository<T> {
  T selectByLibelle(String val);
}
