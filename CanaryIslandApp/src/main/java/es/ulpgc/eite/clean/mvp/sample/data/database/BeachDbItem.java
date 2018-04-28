package es.ulpgc.eite.clean.mvp.sample.data.database;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Luis on 19/11/16.
 */

public class BeachDbItem extends RealmObject{

  private Integer id;
  private String name;


    private String description;


  private RealmList<IslandDbItem> islands;


  public BeachDbItem() {
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
  public boolean equals(Object obj) {
    if (obj instanceof BeachDbItem){
      BeachDbItem item = (BeachDbItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
