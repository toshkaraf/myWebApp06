package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.Config;

/**
 * Created by Антон on 04.11.2015.
 */
public class SerializeFileStorageTest extends AbstractStorageTest{

    public SerializeFileStorageTest() {
        super(new SerializeFileStorage(Config.STORAGE));
    }
}