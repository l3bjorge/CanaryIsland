package es.ulpgc.eite.clean.mvp.sample.data.database;

import io.realm.RealmObject;

/**
 * Created by Luis on 19/11/16.
 */

public class IslandDbItem extends RealmObject{

  private Integer id;
  private String name;

  private BeachDbItem team;
  private CategoryDbItem position;

  public IslandDbItem() {
  }

  public BeachDbItem getTeam() {
    return team;
  }

  public CategoryDbItem getPosition() {
    return position;
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

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof IslandDbItem){
      IslandDbItem item = (IslandDbItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
