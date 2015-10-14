package main.ru.javawebinar.webapp.store;

import main.ru.javawebinar.webapp.WebAppException;
import main.ru.javawebinar.webapp.model.Resume;

/**
 * GKislin
 * 09.10.2015.
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_LENGTH = 100000;
    protected final Resume[] array = new Resume[MAX_LENGTH];
    protected int currentSize = 0;

    protected int getExistedIndex(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            throw new WebAppException("Resume not exist", uuid);
        }
        return idx;
    }

    protected abstract int getIndex(String uuid);


}

