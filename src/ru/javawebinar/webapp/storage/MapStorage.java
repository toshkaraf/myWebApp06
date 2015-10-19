package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * GKislin
 * 16.10.2015.
 */
public class MapStorage extends AbstractMapStorage{
    public Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected boolean exist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected void doUpdate(Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        storage.get(uuid);
        return null;
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

}
