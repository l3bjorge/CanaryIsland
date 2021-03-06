package es.ulpgc.eite.clean.mvp.sample.data.database;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

public class DatabaseFacade {
    
    private static Realm realmDatabase;

    private static Realm getDatabase() {
        if (realmDatabase == null) {
            realmDatabase = Realm.getDefaultInstance();
        }

        return realmDatabase;
    }

    public static JSONObject createJsonObject(int id){   
        Map<String, String> item = new HashMap();
        item.put("id", String.valueOf(id));
        item.put("content", "Item " + id);
        item.put("details", makeDetails(id));
        final JSONObject json = new JSONObject(item);
        Log.d(".createJsonObject", "json=" + json);
        return json;
    }

    public static void deleteDatabase(){
        deleteLocations();
    }


    public static void deleteLocations(){
        for(LocationDbItem item: getLocations()){
            deleteLocation(item);
        }
    }

//    public static void deleteAllDatabaseItems(){
//        for(ModelDbItem item: getItemsFromDatabase()){
//            deleteDatabaseItem(item);
//        }
//    }

    public static void deleteLocation(LocationDbItem item) {
        final Integer id = item.getId();
        getDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(LocationDbItem.class).equalTo("id", id)
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
    }


//    public static void deleteDatabaseItem(ModelDbItem item) {
//        final Integer id = item.getId();
//        getDatabase().executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.where(ModelDbItem.class).equalTo("id", id)
//                        .findAll()
//                        .deleteAllFromRealm();
//            }
//        });
//    }

//    public static void deleteDatabaseItem(Integer id) {
//        final Integer idToRemove = id;
//        //  Required by Java because it is referenced from an inner class
//
//        getDatabase().executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.where(ModelDbItem.class).equalTo("id", idToRemove)
//                        .findAll()
//                        .deleteAllFromRealm();
//            }
//        });
//    }

    public static void deleteLocation(Integer id) {
        final Integer idToRemove = id;
        //  Required by Java because it is referenced from an inner class

        getDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(IslandDbItem.class).equalTo("id", idToRemove)
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
    }

    public static List<LocationDbItem> getLocations(){
        return getDatabase().where(LocationDbItem.class).findAll();
    }

    public static List<CategoryDbItem> getCategories(){
        return getDatabase().where(CategoryDbItem.class).findAll();
    }

//    public static List<ModelDbItem> getItemsFromDatabase(){
//        return getDatabase().where(ModelDbItem.class).findAll();
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position).append("\n");
        for (int count = 0; count < position; count++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    // -------------------------------------------------------------------------------
    //  Fill the Realm database with the contents of a JSON file

    public static void createDbItemsFromJsonFile(Context context, String filename) {
        try {
            InputStream stream = context.getAssets().open(filename);

            // Open a transaction to store items into the realmDatabase
            getDatabase().beginTransaction();

            try {
                getDatabase().createAllFromJson(LocationDbItem.class, stream);
                getDatabase().commitTransaction();

            } catch (IOException e) {
                // Remember to cancel the transaction if anything goes wrong.
                getDatabase().cancelTransaction();
            }

            stream.close();

        } catch (IOException ex) {
            Log.d("DatabaseFacade", "createDbItemsFromJsonFile: file not found");

        }
    }


//    public static void createDbItemsFromJsonFile(Context context, String filename) {
//        try {
//            InputStream stream = context.getAssets().open(filename);
//
//            // Open a transaction to store items into the realmDatabase
//            getDatabase().beginTransaction();
//
//            try {
//                getDatabase().createAllFromJson(ModelDbItem.class, stream);
//                getDatabase().commitTransaction();
//
//            } catch (IOException e) {
//                // Remember to cancel the transaction if anything goes wrong.
//                getDatabase().cancelTransaction();
//            }
//
//            stream.close();
//
//        } catch (IOException ex) {
//            Log.d("DatabaseFacade", "createDbItemsFromJsonFile: file not found");
//
//        }
//    }

    //  -------------------------------------- Unused auxiliary routines

    // Fill dummy contents of the database

    private static final int ITEM_COUNT = 9;
    
    public static void setItemsFromJsonObjectArray(){
        // Add some sample items
        for (int count = 1; count <= ITEM_COUNT; count++) {
            DatabaseFacade.saveFromJsonObject(DatabaseFacade.createJsonObject(count));
        }
    }

    public static void saveFromJsonObject(final JSONObject json) {
        getDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObjectFromJson(LocationDbItem.class, json);
            }
        });
    }


//    public static void saveFromJsonObject(final JSONObject json) {
//        getDatabase().executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.createObjectFromJson(ModelDbItem.class, json);
//            }
//        });
//    }

}
