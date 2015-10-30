package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.Config;

/**
 * GKislin
 * 23.10.2015.
 */
public class DataStreamFileStorageTest extends AbstractStorageTest{

    public DataStreamFileStorageTest() {
        super(new DataStreamFileStorage(Config.STORAGE));
    }
}