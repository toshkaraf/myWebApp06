package ru.javawebinar.webapp.storage;

/**
 * GKislin
 * 09.10.2015.
 */
public class SortedArrayStorageTest extends AbstractStorageTest {
    @Override
    protected IStorage getStorage() {
        return new SortedArrayStorage();
    }
//    public SortedArrayStorageTest() {
//        super(new SortedArrayStorage());
//    }
}
