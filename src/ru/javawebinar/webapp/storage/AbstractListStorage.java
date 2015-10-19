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
    protected boolean exist(String uuid) {
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) return true;
        }
        return false;
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
    protected void doUpdate(Resume r) {
//        storage.remove(r);
        Iterator<Resume> i = storage.iterator();
        while (i.hasNext()) {
            Resume resumeFromList = i.next();
            if (resumeFromList.getUuid().equals(r.getUuid())) {
                i.remove();
                storage.add(r);
                return;
            }
        }
    }

    @Override
    protected Resume doLoad(String uuid) {
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) return r;
        }
        return null;
    }


    @Override
    protected void doDelete(String uuid) {
        Iterator<Resume> i = storage.iterator();
        while (i.hasNext()) {
            Resume resumeFromList = i.next();
            if (resumeFromList.getUuid().equals(uuid)) {
                i.remove();
                return;
            }
        }
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
