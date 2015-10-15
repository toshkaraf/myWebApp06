package main.ru.javawebinar.webapp.store;

import main.ru.javawebinar.webapp.WebAppException;
import main.ru.javawebinar.webapp.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * GKislin
 * 09.10.2015.
 */
public class AbstractArrayStorageTest {
    private Resume R1, R2, R3, R4;
    protected IStorage store = new ArrayStorage();

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
    public void clear(){
        store.clear();
        assertTrue(store.getAllSorted().isEmpty());
    }

    @Test
    public void testBothLoadAndSave() {
      //    assertTrue(store.size());
        assertEquals(3, store.size());
        assertGetResume(R1);
        assertGetResume(R2);
        assertGetResume(R3);
    }

    @Test (expected = Exception.class)
    public void testSaveNotNull() {
        store.save(R4);
    }

    @Test (expected = Exception.class)
    public void testSaveExistedResume(){
        store.save(R3);
    }

    @Test (expected = Exception.class)
    public void testUpdateNotExistedResume() {
        store.update(R4);
    }

    @Test (expected = Exception.class)
    public void testUpdate(){
        R1 = new Resume("Полное Имя11");
        R1.addContact(ContactType.MAIL, "mail1@ya.ua");
        R1.addContact(ContactType.PHONE, "11222");
        store.update(R4);
        assertGetResume(R1);
    }

    @Test (expected = WebAppException.class)
    public void testDelete(){
        store.delete(R2.getUuid());
        store.load(R2.getUuid());
    }

    private void assertGetResume(Resume r) {
        assertTrue(store.load(r.getUuid()).equals(r));
    }
}