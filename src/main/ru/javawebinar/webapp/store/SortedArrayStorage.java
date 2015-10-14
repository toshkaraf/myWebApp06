package main.ru.javawebinar.webapp.store;

import main.ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

/**
 * GKislin
 * 09.10.2015.
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    private String[] sortedUuids = new String[MAX_LENGTH];

    @Override
    public void clear() {
        super.clear();
        Arrays.fill(sortedUuids, null);
    }

    @Override
    public void save(Resume r) {
        //        http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIdx = -(isRequestValid(r)) - 1;
        System.arraycopy(array, insertIdx, array, insertIdx + 1, currentSize - insertIdx);
        System.arraycopy(sortedUuids, insertIdx, sortedUuids, insertIdx + 1, currentSize - insertIdx);
        set(insertIdx, r);
     }

    private void set(int idx, Resume r) {
        array[idx] = r;
        sortedUuids[idx] = (r == null ? null : r.getUuid());
    }

    @Override
    public void update(Resume r) {
        set(getExistedIndex(r.getUuid()), r);
    }

    @Override
    public Resume load(String uuid) {
        return array[getExistedIndex(uuid)];
    }

    @Override
    public void delete(String uuid) {
        int idx = getExistedIndex(uuid);
        int numMoved = currentSize - idx - 1;
        if (numMoved > 0) {
            System.arraycopy(array, idx + 1, array, idx, numMoved);
            System.arraycopy(sortedUuids, idx + 1, sortedUuids, idx, numMoved);
        }
        set(--currentSize, null);
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
        return Arrays.binarySearch(sortedUuids, 0, currentSize, uuid);
    }
}
