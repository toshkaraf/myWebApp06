package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Антон on 20.10.2015.
 */

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super (new MapStorage());
    }
}
