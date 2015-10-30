package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.Config;

public class XmlFileStorageTest extends AbstractStorageTest {
    public XmlFileStorageTest() {
        super(new XmlFileStorage(Config.STORAGE));
    }
}