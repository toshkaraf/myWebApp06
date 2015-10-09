package main.ru.javawebinar.webapp.store;

import main.ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * GKislin
 * 02.10.2015.
 */
public class ArrayStorage implements IStorage {
    private static final int MAX_LENGTH = 100000;

    private final Resume[] array = new Resume[MAX_LENGTH];
    private int size = 0;

    @Override
    public void clear() {
        size = 0;
        Arrays.fill(array, null); // let gc do his work
    }

    @Override
    public void save(Resume r) {
        requireNonNull(r, "Resume must not be null");
        int idx = getIndex(r.getUuid());
        if (idx != -1) {
            throw new IllegalArgumentException("Resume " + r.getUuid() + "already exist");
        }
        if (size == MAX_LENGTH) {
            throw new IllegalStateException("Max storage volume " + MAX_LENGTH + " is exceeded");
        }
        array[size++] = r;
    }

    @Override
    public void update(Resume r) {
        requireNonNull(r);
        array[getExistIndex(r.getUuid())] = r;
    }

    @Override
    public Resume load(String uuid) {
        requireNonNull(uuid);
        return array[getExistIndex(uuid)];
    }

    @Override
    public void delete(String uuid) {
        requireNonNull(uuid);
        array[getExistIndex(uuid)] = array[--size];
        array[size] = null; // clear to let GC do its work
    }

    @Override
    public Collection<Resume> getAllSorted() {
        // TODO implement after collections do sort
        Resume[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy);
    }

    @Override
    public int size() {
        return size;
    }

    private int getExistIndex(String uuid) {
        int idx = getIndex(uuid);
        if (idx == -1) {
            throw new IllegalArgumentException("Resume with " + uuid + "not exist");
        }
        return idx;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (array[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
