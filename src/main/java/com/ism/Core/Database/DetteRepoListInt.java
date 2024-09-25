package com.ism.Core.Database;

import com.ism.entities.Dette;

public interface DetteRepoListInt extends Repository<Dette> {
  Dette selectById(int id);
}
