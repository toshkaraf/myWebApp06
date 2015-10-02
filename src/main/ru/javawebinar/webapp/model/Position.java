package main.ru.javawebinar.webapp.model;

import java.time.LocalDate;

/**
 * GKislin
 * 02.10.2015.
 */
public class Position {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }
}