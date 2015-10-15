package main.ru.javawebinar.webapp.store;

import main.ru.javawebinar.webapp.WebAppException;
import main.ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * GKislin
 * 09.10.2015.
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int MAX_LENGTH = 100000;
    protected final Resume[] array = new Resume[MAX_LENGTH];
    protected int currentSize = 0;


    @Override
    public void clear() {
        Arrays.fill(array, null); // let gc do his work
        currentSize = 0;
    }

    @Override
    public int isRequestValid(Resume r) {
        requireNonNull(r, "Resume must not be null");
        int idx = getIndex(r.getUuid());
        if (idx >= 0) {
            throw new WebAppException("Resume already exist", r);
        }
        if (currentSize == MAX_LENGTH) {
            throw new WebAppException("Max storage volume " + MAX_LENGTH + " is exceeded", r);
        }
        return idx;
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

    protected int getExistedIndex(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            throw new WebAppException("Resume not exist", uuid);
        }
        return idx;
    }

    protected abstract int getIndex(String uuid);

}

