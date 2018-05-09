package es.ulpgc.eite.clean.mvp.sample.data.database;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Luis on 19/11/16.
 */

public class CategoryDbItem extends RealmObject{

  private Integer id;
  private String englishname;
  private String spanishname;
  private String germanname;


  private RealmList<LocationDbItem> locations;


  public CategoryDbItem() {
  }


  public String getEnglishname( ) {
    return englishname;
  }

  public void setEnglishname(String name) {
    this.englishname = name;
  }

  public String getSpanishname() {
    return spanishname;
  }

  public void setSpanishname(String name) {
    this.spanishname = name;
  }

  public String getGermanname() {
    return germanname;
  }

  public void setGermanname(String name) {
    this.germanname = name;
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
