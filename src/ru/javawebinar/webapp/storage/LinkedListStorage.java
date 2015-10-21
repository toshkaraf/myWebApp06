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
//      storage = new LinkedList<>();

    public LinkedListStorage() {
        super(new LinkedList<>());
    }



    @Override
    protected void doDelete(int index, String uuid) {
        storage.remove(index);
//        Iterator<Resume> i = storage.iterator();
//        while (i.hasNext()) {
//            Resume resumeFromList = i.next();
//            if (resumeFromList.getUuid().equals(uuid)) {
//                i.remove();
//                return;
//            }
//        }
    }

    @Override
    protected Resume doLoad(int index, String uuid) {
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) return r;
        }
        return null;
    }


}
