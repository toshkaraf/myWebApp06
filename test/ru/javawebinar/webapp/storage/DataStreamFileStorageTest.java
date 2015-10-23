package ru.javawebinar.webapp.storage;

/**
 * GKislin
 * 23.10.2015.
 */
public class DataStreamFileStorageTest extends AbstractStorageTest{

    public DataStreamFileStorageTest() {
        super(new DataStreamFileStorage("./storage"));
    }
}