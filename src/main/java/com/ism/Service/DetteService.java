package com.ism.Service;

import java.util.List;

import com.ism.Core.Database.DetteRepoListInt;
import com.ism.entities.Dette;
import com.ism.enums.EtatDette;

import lombok.Data;

@Data
public class DetteService implements DetteServiceint<Dette,DetteRepoListInt> {

  private DetteRepoListInt detteRepo;

  
  public DetteService(DetteRepoListInt detteRepo) {
    this.detteRepo = detteRepo;
  }
  @Override
  public void archiverSolider() {
    for (Dette dette : detteRepo.selectAll()) {
      if (dette.getMontantRestant() == 0) {
        dette.setEtat(EtatDette.Archiver);
      }
    }

  }

  @Override
  public boolean saveList(Dette objet) {
    if(objet != null){
      detteRepo.insert(objet);
      return true;
    }
    return false;
  }

  @Override
  public List<Dette> show() {
    return detteRepo.selectAll();
  }
  @Override
  public DetteRepoListInt findData() {
    return detteRepo;
  }
}
