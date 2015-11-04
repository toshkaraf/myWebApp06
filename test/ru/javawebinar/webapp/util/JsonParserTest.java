package ru.javawebinar.webapp.util;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.webapp.Config;
import ru.javawebinar.webapp.ResumeTestData;
import ru.javawebinar.webapp.model.Resume;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static ru.javawebinar.webapp.ResumeTestData.R1;
import static ru.javawebinar.webapp.ResumeTestData.R2;
import static ru.javawebinar.webapp.ResumeTestData.R3;

/**
 * GKislin
 * 30.10.2015.
 */
public class JsonParserTest {
    @Before
    public void setUp() throws Exception {
        ResumeTestData.init();
    }

    @Test
    public void testReadAndWrite() throws Exception {
        readAndWrite("resume1.json", R1);
        readAndWrite("resume2.json", R2);
        readAndWrite("resume3.json", R3);
    }

    public void readAndWrite(String filename, Resume r) throws Exception {
        File file = new File(Config.STORAGE, filename);
        try (FileWriter writer = new FileWriter(file)) {
            JsonParser.write(r, writer);
        }
        try (FileReader reader = new FileReader(file)) {
            Resume result = JsonParser.read(reader, Resume.class);
            System.out.println(result);
        }
    }
}