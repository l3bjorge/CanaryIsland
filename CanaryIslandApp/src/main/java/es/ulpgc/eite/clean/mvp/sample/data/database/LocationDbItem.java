package es.ulpgc.eite.clean.mvp.sample.data.database;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Luis on 19/11/16.
 */

public class LocationDbItem extends RealmObject{

  private Integer id;
  private String name;


    private String description;
    private String map;
    private String web;
    private String facebook;
    private String faceurl;
    private String instagram;
    private String instagramurl;


  private CategoryDbItem category;
  private IslandDbItem island;


  public LocationDbItem() {
  }

  public CategoryDbItem getCategory() {
    return category;
  }

  public IslandDbItem getIsland() {
    return island;
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

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
  }

  public String getWeb() {
    return web;
  }

  public void setWeb(String web) {
    this.web = web;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public String getFaceurl() {
    return faceurl;
  }

  public void setFaceurl(String faceurl) {
    this.faceurl = faceurl;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  public String getInstagramurl() {
    return instagramurl;
  }

  public void setInstagramurl(String instagramurl) {
    this.instagramurl = instagramurl;
  }


    @Override
  public boolean equals(Object obj) {
    if (obj instanceof LocationDbItem){
      LocationDbItem item = (LocationDbItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
