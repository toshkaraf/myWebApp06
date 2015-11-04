package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.Config;

import static org.junit.Assert.*;

/**
 * Created by Антон on 04.11.2015.
 */
public class JsonFileStorageTest extends AbstractStorageTest{
    public JsonFileStorageTest() {
        super(new JsonFileStorage(Config.STORAGE));
    }
}