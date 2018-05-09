package es.ulpgc.eite.clean.mvp.sample.data;


import es.ulpgc.eite.clean.mvp.sample.data.database.IslandDbItem;
import es.ulpgc.eite.clean.mvp.sample.data.database.LocationDbItem;

public class LocationItem {

  private LocationDbItem dbItem;

  //  Disabling the default constructor
  private LocationItem() {

  }

  public LocationItem(LocationDbItem dbItem) {
    this.dbItem = dbItem;
  }

  public String getTitle() {
    return dbItem.getName();
  }

  public LocationDbItem getDbItem() {
    return dbItem;
  }

  public String getCategory(String language) {

    String category = "";

    if (language.equals("English")) {
      category = dbItem.getCategory().getEnglishname();
    } else if(language.equals("Spanish")){
      category = dbItem.getCategory().getSpanishname();
    } else if(language.equals("German")){
      category = dbItem.getCategory().getGermanname();
    }

    return category;
  }

  public String getDescription() {
    String description = dbItem.getDescription();

    return description;
  }

  public String getId() {
    return dbItem.getId().toString();
  }

  @Override
  public String toString() {
    return this.getTitle();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof LocationItem){
      LocationItem item = (LocationItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
