package ru.javawebinar.webapp.model;

/**
 * GKislin
 * 27.09.2015.
 */
public class Link {
    private final String name;
    private final String url;

    public Link(String name, String url) {
        if (name!=null) this.name = name;
        else this.name = "";
        if (url!=null) this.url = url;
        else this.url = "";
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Link link = (Link) o;

        if (!name.equals(link.name)) {
            return false;
        }
        return !(url != null ? !url.equals(link.url) : link.url != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
