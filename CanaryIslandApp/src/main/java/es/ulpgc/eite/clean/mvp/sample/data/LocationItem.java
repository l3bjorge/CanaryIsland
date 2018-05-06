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

  public String getContent() {
    return dbItem.getName();
  }

  public LocationDbItem getDbItem() {
    return dbItem;
  }

  public String getDetails() {
    String island = dbItem.getIsland().getName();
    String category = dbItem.getCategory().getName();

    return "Beach: " + island + "\n" + "Category: " + category;
  }

  public String getId() {
    return dbItem.getId().toString();
  }

  @Override
  public String toString() {
    return this.getContent();
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
