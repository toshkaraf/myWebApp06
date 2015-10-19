package ru.javawebinar.webapp.storage;

import java.util.ArrayList;

/**
 * Created by Антон on 20.10.2015.
 */
public class ArrayListStorage extends AbstractListStorage{
    public ArrayListStorage() {
        super(new ArrayList<>());
    }
}
