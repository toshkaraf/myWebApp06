package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.exceptions.ExceptionType;
import ru.javawebinar.webapp.exceptions.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * GKislin
 * 09.10.2015.
 */
public abstract class AbstractStorage implements IStorage {
    //    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected final Logger log = Logger.getLogger(getClass().getName());

    protected abstract int exist(String uuid);

    @Override
    public void clear() {
        log.info("clear");
        doClear();
    }

    protected abstract void doClear();

    @Override
    public void save(Resume r) {
        log.info("Save " + r);
        requireNonNull(r, "Resume must not be null");
        mustNotExist(r.getUuid());
        doSave(r);
    }

    protected abstract void doSave(Resume r);

    @Override
    public void update(Resume r) {
        log.info("Update " + r);
        requireNonNull(r, "Resume must not be null");
        int index = mustExist(r.getUuid());
        doUpdate(index,r);
    }

    protected abstract void doUpdate(int index,Resume r);

    @Override
    public Resume load(String uuid) {
        log.info("Load " + uuid);
        requireNonNull(uuid, "UUID must not be null");
        int index = mustExist(uuid);
        return doLoad(index, uuid);
    }

    protected abstract Resume doLoad(int index, String uuid);

    @Override
    public void delete(String uuid) {
        log.info("Delete " + uuid);
        requireNonNull(uuid, "UUID must not be null");
        int index = mustExist(uuid);
        doDelete(index, uuid);
    }

    protected abstract void doDelete(int index, String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        log.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        return list;
    }

    protected abstract List<Resume> doGetAll();

    private void mustNotExist(String uuid) {
        if (exist(uuid)>=0) {
            throw new WebAppException(ExceptionType.ALREADY_EXISTS, uuid);
        }
    }

    private int mustExist(String uuid) {
        int index = exist(uuid);
        if (index<0){
            throw new WebAppException(ExceptionType.NOT_FOUND, uuid);
        }
        else return index;
    }
}
