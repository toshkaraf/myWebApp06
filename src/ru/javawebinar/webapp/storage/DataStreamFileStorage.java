package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.exceptions.ExceptionType;
import ru.javawebinar.webapp.exceptions.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * GKislin
 * 23.10.2015.
 */
public class DataStreamFileStorage extends AbstractFileStorage {

    protected final File directory;

    public DataStreamFileStorage(String path) {
        directory = new File(path);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(path + " is not directory");
        }
    }

    @Override
    protected File getContext(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean exist(String uuid, File file) {
        return file.isFile();
    }

    @Override
    protected void doClear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file.getName(), file);
            }
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            if (!file.createNewFile()) {
                throw new WebAppException(ExceptionType.IO_ERROR, r.getUuid());
            }
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeUTF(r.getFullName());
                dos.writeInt(r.getContacts().size());
                for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                    dos.writeUTF(entry.getKey().name());
                    dos.writeUTF(entry.getValue());
                }
            }
            //TODO implements section
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, r.getUuid());
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {

    }

    @Override
    protected Resume doLoad(String uuid, File file) {
        try {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                String fullName = dis.readUTF();
                int contactSize = dis.readInt();
                Resume r = new Resume(uuid, fullName);
                for (int i = 0; i < contactSize; i++) {
                    r.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
                }
                //TODO implements section
                return r;
            }
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, uuid);
        }
    }

    @Override
    protected void doDelete(String uuid, File file) {
        if (!file.delete()) {
            throw new WebAppException(ExceptionType.IO_ERROR, uuid);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
