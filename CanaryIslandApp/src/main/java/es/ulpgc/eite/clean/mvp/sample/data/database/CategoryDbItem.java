package es.ulpgc.eite.clean.mvp.sample.data.database;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Luis on 19/11/16.
 */

public class CategoryDbItem extends RealmObject{

  private Integer id;
  private String name;


  private RealmList<LocationDbItem> locations;


  public CategoryDbItem() {
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
    if (obj instanceof CategoryDbItem){
      CategoryDbItem item = (CategoryDbItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
