package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;

/**
 * Created by Антон on 20.10.2015.
 */
public class ArrayListStorage extends AbstractListStorage {
    ArrayList<Resume> storage = new ArrayList<>();

    public ArrayListStorage() {
        super(new ArrayList<>());
    }

    @Override
    protected void doUpdate(int index, Resume r) {
        storage.add(index, r);
    }

    @Override
    protected Resume doLoad(int index, String uuid) {
       return storage.get(index);
    }

    @Override
    protected void doDelete(int index, String uuid) {
        storage.remove(index);
    }
}