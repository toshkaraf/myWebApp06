package main.ru.javawebinar.webapp.store;

import main.ru.javawebinar.webapp.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * GKislin
 * 09.10.2015.
 */
public class AbstractStorageTest {
    private Resume R1, R2, R3;
    private IStorage store = new SortedArrayStorage();

    {
        R1 = new Resume("Полное Имя1");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R2 = new Resume("Полное Имя2");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R3 = new Resume("Полное Имя3");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.ACHIEVEMENT, new MultiTextSection("Achivment11", "Achivment12"));
        R1.addSection(SectionType.QUALIFICATIONS, new MultiTextSection("Java", "SQL"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", null,
                                new Position(2005, Month.JANUARY, "position1", "content1"),
                                new Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
    }

    @Before
    public void setUp() throws Exception {
        store.clear();
        store.save(R1);
        store.save(R2);
        store.save(R3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoad() throws Exception {
        assertTrue(store.size() == 3);
        assertGetResume(R1);
        assertGetResume(R2);
        assertGetResume(R3);
    }

    @Test
    public void testSave() throws Exception {
        assertEquals("expected", "actual");
    }

    private void assertGetResume(Resume r) {
        assertTrue(store.load(r.getUuid()).equals(r));
    }
}