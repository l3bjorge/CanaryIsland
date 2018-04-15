package es.ulpgc.eite.clean.mvp.sample.data;

public class ModelIdItem {

    private Integer Id;
    private String name;
    private String description;
    private String location;
    private String web;
    private String facebook;
    private String instagram;


    public ModelIdItem(){
        this.name = "";
        this.description = "";
        this.location = "";
        this.web = "";
        this.facebook = "";
        this.instagram = "";

    }

    public ModelIdItem(String name, String description, String location, String web, String facebook, String instagram){
        this.name = name;
        this.description = description;
        this.location = location;
        this.web = web;
        this.facebook = facebook;
        this.instagram = instagram;
    }

    @Override
    public String toString(){
        return name;

    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof ModelIdItem){
            ModelIdItem item = (ModelIdItem) obj;
            if (item.getId() == getId()){
                return true;
            }
        }

        return false;
    }

    //////////////////////////////////////////////////////////////////////////////

    public Integer getId(){
        return Id;
    }
    public void setId(Integer id){
        this.Id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
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

    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

}
