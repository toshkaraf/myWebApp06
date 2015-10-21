package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Антон on 19.10.2015.
 */
public abstract class AbstractListStorage extends AbstractStorage {
    protected List<Resume> storage;

    protected AbstractListStorage(List<Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected int exist(String uuid) {
        int index = 0;
        for (Resume r : storage){
            if (uuid.equals(r.getUuid())) return index;
                index++;
        }
        return -1;
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume r) {
        storage.add(r);
    }

     @Override
    protected List<Resume> doGetAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

}
