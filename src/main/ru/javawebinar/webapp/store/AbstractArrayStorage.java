package main.ru.javawebinar.webapp.store;

/**
 * GKislin
 * 09.10.2015.
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_LENGTH = 100000;

    protected int getExistedIndex(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            throw new IllegalStateException("Resume " + uuid + "not exist");
        }
        return idx;
    }

    protected abstract int getIndex(String uuid);
}

