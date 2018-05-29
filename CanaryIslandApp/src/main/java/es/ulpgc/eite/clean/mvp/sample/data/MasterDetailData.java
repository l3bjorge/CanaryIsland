package es.ulpgc.eite.clean.mvp.sample.data;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.sample.data.database.CategoryDbItem;
import es.ulpgc.eite.clean.mvp.sample.data.database.DatabaseFacade;
import es.ulpgc.eite.clean.mvp.sample.data.database.LocationDbItem;

public class MasterDetailData {

    private static final String TAG = "MasterDetailData";

    public static void deleteAllDatabaseItems(){
        for(LocationItem item: getItemsFromDatabase()){
            deleteItem(item);
        }
    }

//    public static void deleteItem(ModelItem item) {
//        DatabaseFacade.deleteDatabaseItem(item.getDbItem());
//    }

    public static void deleteItem(LocationItem item) {
        DatabaseFacade.deleteLocation(item.getDbItem());
    }

    public static List<LocationItem> getItemsFromDatabase(){
        Log.d(TAG, "calling getItemsFromDatabase() method");


        List<LocationDbItem> dbItems = DatabaseFacade.getLocations();
        //Log.d(TAG, "items=" +  dbItems);

        // Adapt the contents of low-level ModelDbItems to high-level ModelItem components.

        List<LocationItem> modelItems = new ArrayList();
        for(LocationDbItem dbItem: dbItems) {
            modelItems.add(new LocationItem(dbItem));
        }

        Log.d(TAG, "items=" +  modelItems);
        return modelItems;
    }

    public static List<CategoryItem> getCategoryItemsFromDatabase(){
        Log.d(TAG, "calling getCategoryItemsFromDatabase() method");


        List<CategoryDbItem> dbItems = DatabaseFacade.getCategories();
        //Log.d(TAG, "items=" +  dbItems);

        // Adapt the contents of low-level ModelDbItems to high-level ModelItem components.

        List<CategoryItem> modelItems = new ArrayList();
        for(CategoryDbItem dbItem: dbItems) {
            modelItems.add(new CategoryItem(dbItem));
        }

        Log.d(TAG, "items=" +  modelItems);
        return modelItems;
    }


//    public static List<ModelItem> getItemsFromDatabase(){
//        Log.d(TAG, "calling getItemsFromDatabase() method");
//
//        List<ModelDbItem> dbItems = DatabaseFacade.getItemsFromDatabase();
//        //Log.d(TAG, "items=" +  dbItems);
//
//        // Adapt the contents of low-level ModelDbItems to high-level ModelItem components.
//
//        List<ModelItem> modelItems = new ArrayList();
//        for(ModelDbItem dbItem: dbItems) {
//            modelItems.add(new ModelItem(dbItem));
//        }
//
//        Log.d(TAG, "items=" +  modelItems);
//        return modelItems;
//    }

    public static void loadItemsFromJsonFile(Context context, String filename) {
        DatabaseFacade.createDbItemsFromJsonFile(context, filename);
    }
}
