package main;

import main.ru.javawebinar.webapp.model.Link;

import java.util.List;

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
        Link link = new Link();
        System.out.println(link.getName() + " " + link.getUrl());
    }
}
