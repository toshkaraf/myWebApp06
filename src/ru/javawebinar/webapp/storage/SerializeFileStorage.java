package ru.javawebinar.webapp.storage;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import ru.javawebinar.webapp.exceptions.ExceptionType;
import ru.javawebinar.webapp.exceptions.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.io.*;

/**
 * GKislin
 * 30.10.2015.
 */
public class SerializeFileStorage extends AbstractFileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            Resume r = (Resume) ois.readObject();
            return r;
        } catch (ClassNotFoundException e) {
            throw new WebAppException(ExceptionType.SERIALISATION_ERROR, "Class not found", e);
        }
    }
}
