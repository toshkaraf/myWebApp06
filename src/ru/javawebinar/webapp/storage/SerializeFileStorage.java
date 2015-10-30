package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * GKislin
 * 30.10.2015.
 */
// TODO implements with ObjectOutputStream, ObjectInputStream
public class SerializeFileStorage extends AbstractFileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(Resume r, OutputStream os) throws IOException {

    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        return null;
    }
}
