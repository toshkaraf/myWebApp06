package main.ru.javawebinar.webapp.model;

/**
 * GKislin
 * 27.09.2015.
 */
public class Link {
    public int i = 5;
    public String name;
    public String url;

    public Link() {
        this(null, null);
    }

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
