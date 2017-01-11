package model.dao.dataStore;

import java.util.List;

public abstract class AbstractDataSore
        implements DataStore {

    @Override
    public int add(Object obj) {
        return getDataStore().add(obj);
    }

    @Override
    public int update(Object obj) {
        return getDataStore().update(obj);
    }

    @Override
    public int delete(Object obj) {
        return getDataStore().delete(obj);
    }

    @Override
    public Object getObject(Object obj) {
        return getDataStore().getObject(obj);
    }

    @Override
    public List getObjects(Object obj) {
        return getDataStore().getObjects(obj);
    }

    @Override
    public List select(Object obj) {
        return getDataStore().select(obj);
    }

    @Override
    public List query(Object obj, int type, String condicion){
        return getDataStore().query(obj, type, condicion);
    }
    public abstract DataStore getDataStore();
}