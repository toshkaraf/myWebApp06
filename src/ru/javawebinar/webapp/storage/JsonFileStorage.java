package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.util.JsonParser;

import java.io.*;

/**
 * GKislin
 * 30.10.2015.
 */
// TODO Optional try implements section
public class JsonFileStorage extends AbstractFileStorage{

    public JsonFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(Resume r, OutputStream os) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
            JsonParser.write(r, writer);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(is)) {
            Resume r = JsonParser.read(reader, Resume.class);
            return r;
        }
    }

}
