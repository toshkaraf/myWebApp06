package main.ru.javawebinar.webapp.store;

import main.ru.javawebinar.webapp.WebAppException;
import main.ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * GKislin
 * 02.10.2015.
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        isRequestValid(r);
        array[currentSize] = r;
    }
    @Override
    public void update(Resume r) {
        requireNonNull(r);
        array[getExistedIndex(r.getUuid())] = r;
    }

    @Override
    public Resume load(String uuid) {
        requireNonNull(uuid);
        return array[getExistedIndex(uuid)];
    }

    @Override
    public void delete(String uuid) {
        requireNonNull(uuid);
        array[getExistedIndex(uuid)] = array[--currentSize];
        array[currentSize] = null; // clear to let GC do its work
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < currentSize; i++) {
            if (array[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
