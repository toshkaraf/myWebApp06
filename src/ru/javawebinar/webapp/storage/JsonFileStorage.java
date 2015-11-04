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
        JsonParser.write(r, new OutputStreamWriter(os));
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        return JsonParser.read(new InputStreamReader(is),Resume.class);
    }

}
