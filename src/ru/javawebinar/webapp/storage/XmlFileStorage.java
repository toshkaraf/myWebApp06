package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.*;
import ru.javawebinar.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * GKislin
 * 30.01.2015.
 */
// TODO fix
public class XmlFileStorage extends AbstractFileStorage {
    private XmlParser xmlParser;

    public XmlFileStorage(String path) {
        super(path);
        xmlParser = new XmlParser(
                Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class, MultiTextSection.class, Organization.Position.class);
    }

    @Override
    protected void write(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
