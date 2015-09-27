package main;

import main.ru.javawebinar.webapp.model.Link;

/**
 * User: gkislin
 * Date: 18.06.2014
 */
public class Main {
    /**
     * First java program
     *
     * @param args : program arguments
     */
    public static void main(String[] args) {
        Link link = new Link("Siemens", "link");
        new Main().test(link.i, link.getName(), link);
        System.out.println(link.i + " " + link.getName() + " " + link.getUrl());
    }

    public void test(int i, String name, Link link) {
        link.i = 7;
        name = "Alcatel";
        link.url = "link2";
        throw new RuntimeException();
    }
}
