package main;

import main.ru.javawebinar.webapp.model.Link;

import java.util.Arrays;

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
        System.out.println("equals: " + (link.getClass() == Link.class));
        System.out.println(link.toString());
        System.out.println(Arrays.toString(link.getClass().getMethods()));
    }

    public void test(int i, String name, Link link) {
        new Integer(i).hashCode();
    }
}
