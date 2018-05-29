package es.ulpgc.eite.clean.mvp.sample.data;

import es.ulpgc.eite.clean.mvp.sample.data.database.CategoryDbItem;

public class CategoryItem {
    private CategoryDbItem dbItem;

    private CategoryItem() {

    }


    public CategoryItem(CategoryDbItem dbItem) {
        this.dbItem = dbItem;
    }


    public String getEnglishName() {
        return dbItem.getEnglishname();
    }

    public String getSpanishName() {
        return dbItem.getSpanishname();
    }

    public String getGermanName() {
        return dbItem.getGermanname();
    }

    public CategoryDbItem getDbItem() {
        return dbItem;
    }

    public String getId() {
        return dbItem.getId().toString();
    }

    @Override
    public String toString() {
        return this.getSpanishName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CategoryItem){
            CategoryItem item = (CategoryItem) obj;
            if(item.getId() == getId()){
                return true;
            }
        }
        return false;
    }
}

