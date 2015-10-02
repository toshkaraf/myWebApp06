package main.ru.javawebinar.webapp.model;

import java.util.Collection;
import java.util.Map;

/**
 * GKislin
 * 18.09.2015.
 */
public class Resume {
    private Map<ContactType, String> contacts;

    public Resume(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }
}
