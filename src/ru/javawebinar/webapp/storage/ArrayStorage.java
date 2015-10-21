package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

/**
 * GKislin
 * 02.10.2015.
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < currentSize; i++) {
            if (array[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void set(int idx, Resume r) {
        array[idx] = r;
    }

    @Override
    protected void doSave(Resume r) {
        checkExceeded(r);
        array[currentSize++] = r;
    }

    @Override
    protected void doDelete(String uuid) {
        array[getIndex(uuid)] = array[--currentSize];
        array[currentSize] = null;
    }
}
