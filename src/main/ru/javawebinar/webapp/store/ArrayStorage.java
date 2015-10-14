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
    public void clear() {
        currentSize = 0;
        Arrays.fill(array, null); // let gc do his work
    }

    @Override
    public void save(Resume r) {
        log.info("save " + r);
        requireNonNull(r, "Resume must not be null");
        int idx = getIndex(r.getUuid());
        if (idx != -1) {
            throw new WebAppException("Resume already exist", r);
        }
        if (currentSize == MAX_LENGTH) {
            throw new WebAppException("Max storage volume " + MAX_LENGTH + " is exceeded", r);
        }
        array[currentSize++] = r;
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
    public Collection<Resume> getAllSorted() {
        // TODO implement after collections do sort
        Resume[] copy = Arrays.copyOf(array, currentSize);
        return Arrays.asList(copy);
    }

    @Override
    public int size() {
        return currentSize;
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
