package main.ru.javawebinar.webapp.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * GKislin
 * 02.10.2015.
 */
public class MultiTextSection {
    private final List<String> lines;

    public MultiTextSection(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }
}
