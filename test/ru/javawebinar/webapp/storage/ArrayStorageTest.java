package ru.javawebinar.webapp.storage;

/**
 * GKislin
 * 09.10.2015.
 */
public class ArrayStorageTest extends AbstractStorageTest {
    @Override
    protected IStorage getStorage() {
        return new ArrayStorage();
    }
//    public ArrayStorageTest() {
//        super(new ArrayStorage());
//    }
}
