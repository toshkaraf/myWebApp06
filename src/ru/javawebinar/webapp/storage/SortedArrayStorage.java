package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;

/**
 * GKislin
 * 09.10.2015.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    private String[] sortedUuids = new String[MAX_LENGTH];

    @Override
    public void doClear() {
        Arrays.fill(sortedUuids, 0, currentSize, null);
        super.doClear();
    }

    @Override
    protected void set(int idx, Resume r) {
        array[idx] = r;
        sortedUuids[idx] = (r == null ? null : r.getUuid());
    }

    @Override
    public void doSave(Resume r) {
        checkExceeded(r);
//        http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIdx = -getIndex(r.getUuid()) - 1;
        System.arraycopy(array, insertIdx, array, insertIdx + 1, currentSize - insertIdx);
        System.arraycopy(sortedUuids, insertIdx, sortedUuids, insertIdx + 1, currentSize - insertIdx);
        set(insertIdx, r);
        currentSize++;
    }

    @Override
    public void doDelete(int index, String uuid) {
        int numMoved = currentSize - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
            System.arraycopy(sortedUuids, index + 1, sortedUuids, index, numMoved);
        }
        set(--currentSize, null);
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(sortedUuids, 0, currentSize, uuid);
    }
}
