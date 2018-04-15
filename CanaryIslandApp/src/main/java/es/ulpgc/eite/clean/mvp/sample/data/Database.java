package es.ulpgc.eite.clean.mvp.sample.data;

import java.util.List;

public interface Database {
    ModelIdItem getItem(Integer id);

    List<ModelIdItem> getAllItemsFromDatabase();
    ModelIdItem[] getAllItemsArrayFromDatabase();

}
