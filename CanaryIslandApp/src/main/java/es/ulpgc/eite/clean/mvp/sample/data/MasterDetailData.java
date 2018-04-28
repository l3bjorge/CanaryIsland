package es.ulpgc.eite.clean.mvp.sample.data;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.sample.data.database.DatabaseFacade;
import es.ulpgc.eite.clean.mvp.sample.data.database.IslandDbItem;

public class MasterDetailData {

    private static final String TAG = "MasterDetailData";

    public static void deleteAllDatabaseItems(){
        for(IslandItem item: getItemsFromDatabase()){
            deleteItem(item);
        }
    }

//    public static void deleteItem(ModelItem item) {
//        DatabaseFacade.deleteDatabaseItem(item.getDbItem());
//    }

    public static void deleteItem(IslandItem item) {
        DatabaseFacade.deletePlayer(item.getDbItem());
    }

    public static List<IslandItem> getItemsFromDatabase(){
        Log.d(TAG, "calling getItemsFromDatabase() method");


        List<IslandDbItem> dbItems = DatabaseFacade.getPlayers();
        //Log.d(TAG, "items=" +  dbItems);

        // Adapt the contents of low-level ModelDbItems to high-level ModelItem components.

        List<IslandItem> modelItems = new ArrayList();
        for(IslandDbItem dbItem: dbItems) {
            modelItems.add(new IslandItem(dbItem));
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
