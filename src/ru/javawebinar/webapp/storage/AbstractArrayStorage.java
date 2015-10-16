package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.exceptions.ExceptionType;
import ru.javawebinar.webapp.exceptions.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 09.10.2015.
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_LENGTH = 100000;
    protected final Resume[] array = new Resume[MAX_LENGTH];
    protected int currentSize = 0;

    protected abstract int getIndex(String uuid);

    @Override
    protected boolean exist(String uuid) {
        return getIndex(uuid) >= 0;
    }

    @Override
    protected void doClear() {
        Arrays.fill(array, 0, currentSize, null);
        currentSize = 0;
    }

    protected abstract void set(int idx, Resume r);

    @Override
    protected Resume doLoad(String uuid) {
        return array[getIndex(uuid)];
    }

    @Override
    protected void doUpdate(Resume r) {
        set(getIndex(r.getUuid()), r);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    protected List<Resume> doGetAll() {
        Resume[] copy = Arrays.copyOf(array, currentSize);
        return Arrays.asList(copy);
    }

    protected void checkExceeded(Resume r) {
        if (currentSize == MAX_LENGTH) {
            throw new WebAppException(ExceptionType.MAX_VOLUME_EXCEEDED, r.getUuid());
        }
    }
}

