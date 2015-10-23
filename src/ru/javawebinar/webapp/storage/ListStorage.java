package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    protected int getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean exist(String uuid) {
        return getIndex(uuid) >= 0;
    }

    @Override
    protected void doClear() {
        list.clear();
    }

    @Override
    protected void doSave(Resume r) {
        list.add(r);
    }

    @Override
    protected void doUpdate(Resume r) {
        list.set(getIndex(r.getUuid()), r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return list.get(getIndex(uuid));
    }

    @Override
    protected void doDelete(String uuid) {
        list.remove(getIndex(uuid));
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}
