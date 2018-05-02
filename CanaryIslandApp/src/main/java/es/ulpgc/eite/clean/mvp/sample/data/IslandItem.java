package es.ulpgc.eite.clean.mvp.sample.data;


import es.ulpgc.eite.clean.mvp.sample.data.database.IslandDbItem;

public class IslandItem {

  private IslandDbItem dbItem;

  //  Disabling the default constructor
  private IslandItem() {

  }

  public IslandItem(IslandDbItem dbItem) {
    this.dbItem = dbItem;
  }

  public String getContent() {
    return dbItem.getName();
  }

  public IslandDbItem getDbItem() {
    return dbItem;
  }

  public String getDetails() {
    //String beach = dbItem.getBeach().getName();
    //String category = dbItem.getCategory().getName();

    return "Beach: " /*+ beach + "\n" + "Category: " + category*/;
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
    if (obj instanceof IslandItem){
      IslandItem item = (IslandItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
