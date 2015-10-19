package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Антон on 19.10.2015.
 */
public class LinkedListStorage extends AbstractListStorage {


    public LinkedListStorage() {
//        List<Resume> storage = new LinkedList<>();
        super(new LinkedList<>());
    }

}
