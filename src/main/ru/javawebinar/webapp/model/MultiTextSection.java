package main.ru.javawebinar.webapp.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * GKislin
 * 02.10.2015.
 */
public class MultiTextSection {
    private List<String> lines = new LinkedList<>();

    public MultiTextSection(String... values) {
        System.out.println(values);
    }
}
