package es.ulpgc.eite.clean.mvp.sample.data;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFacade implements Database {

    private ArrayList<ModelIdItem> data;

    private DatabaseFacade(){
        data = new ArrayList<ModelIdItem>();
    }

    private static DatabaseFacade instance;
    public static DatabaseFacade getInstance(){
        if (instance == null){
            instance = new DatabaseFacade();
        }
        return instance;
    }

    @Override
    public ModelIdItem getItem(Integer id) {
        return data.get(id);
    }

    @Override
    public List<ModelIdItem> getAllItemsFromDatabase() {
        ArrayList<ModelIdItem> result = new ArrayList<ModelIdItem>();

        for (int id = 0; id < data.size(); id++){
            result.add(data.get(id));
        }
        return result;
    }

    @Override
    public ModelIdItem[] getAllItemsArrayFromDatabase() {
        int length = 0;

        for (int id = 0; id < data.size(); id++){
            length++;
        }

        ModelIdItem[] result = new ModelIdItem[length];
        int pos = 0;
        for (int id = 0; id < data.size(); id++){
            result[pos] = data.get(id);
            pos++;
        }


        return result;
    }

}
