package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.exceptions.ExceptionType;
import ru.javawebinar.webapp.exceptions.WebAppException;
import ru.javawebinar.webapp.model.*;
import ru.javawebinar.webapp.model.Organization.Position;

import java.io.*;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.webapp.model.SectionType.*;

/**
 * GKislin
 * 23.10.2015.
 */
public class DataStreamFileStorage extends AbstractFileStorage {

    protected final File directory;
    protected int size = 0;

    public DataStreamFileStorage(String path) {
        directory = new File(path);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(path + " is not directory");
        }
    }

    @Override
    protected File getContext(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean exist(String uuid, File file) {
        return file.isFile();
    }

    @Override
    protected void doClear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file.getName(), file);
            }
        }
        size = 0;
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            if (!file.createNewFile()) {
                throw new WebAppException(ExceptionType.IO_ERROR, r.getUuid());
            }
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeUTF(r.getFullName());
                dos.writeInt(r.getContacts().size());
                for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                    dos.writeUTF(entry.getKey().name());
                    dos.writeUTF(entry.getValue());
                }
                Set<Map.Entry<SectionType, Section>> entrySet = r.getSections().entrySet();
                dos.writeInt(entrySet.size());
                for (Map.Entry<SectionType, Section> entry : entrySet) {
                    SectionType sectionType = entry.getKey();
                    switch (sectionType) {
                        case OBJECTIVE:
                            dos.writeUTF(OBJECTIVE.name());
                            dos.writeUTF(((TextSection) entry.getValue()).getContent());
                            break;
                        case ACHIEVEMENT:
                            dos.writeUTF(ACHIEVEMENT.name());
                            MultiTextSection achievementTextSection = (MultiTextSection) entry.getValue();
                            List<String> ats = achievementTextSection.getLines();
                            dos.writeInt(ats.size());
                            for (String line : ats) dos.writeUTF(line);
                            break;
                        case QUALIFICATIONS:
                            dos.writeUTF(QUALIFICATIONS.name());
                            MultiTextSection qualificationMultiTextSection = (MultiTextSection) entry.getValue();
                            List<String> qmts = qualificationMultiTextSection.getLines();
                            dos.writeInt(qmts.size());
                            for (String line : qmts) dos.writeUTF(line);
                            break;
                        case EXPERIENCE:
                            dos.writeUTF(EXPERIENCE.name());
                            OrganizationSection experienceOrganizationSection = (OrganizationSection) entry.getValue();
                            List<Organization> experienceOS = experienceOrganizationSection.getOrganizations();
                            dos.writeInt(experienceOS.size());
                            for (Organization org : experienceOS) {
                                dos.writeUTF(org.getHomePage().getName());
                                dos.writeUTF(org.getHomePage().getUrl());
                                List<Position> positions = org.getPositions();
                                dos.writeInt(positions.size());
                                for (Position pos : positions) {
                                    dos.writeInt(pos.getStartDate().getYear());
                                    dos.writeInt(pos.getStartDate().getMonthValue());
                                    dos.writeInt(pos.getEndDate().getYear());
                                    dos.writeInt(pos.getEndDate().getMonthValue());
                                    dos.writeUTF(pos.getTitle());
                                    dos.writeUTF(pos.getDescription());
                                }
                            }
                            break;
                        case EDUCATION:
                            dos.writeUTF(EDUCATION.name());
                            OrganizationSection educationalOrganizationSection = (OrganizationSection) entry.getValue();
                            List<Organization> educationOS = educationalOrganizationSection.getOrganizations();
                            dos.writeInt(educationOS.size());
                            for (Organization org : educationOS) {
                                dos.writeUTF(org.getHomePage().getName());
                                dos.writeUTF(org.getHomePage().getUrl());
                                List<Position> positions = org.getPositions();
                                dos.writeInt(positions.size());
                                for (Position pos : positions) {
                                    dos.writeInt(pos.getStartDate().getYear());
                                    dos.writeInt(pos.getStartDate().getMonthValue());
                                    dos.writeInt(pos.getEndDate().getYear());
                                    dos.writeInt(pos.getEndDate().getMonthValue());
                                    dos.writeUTF(pos.getTitle());
                                    dos.writeUTF(pos.getDescription());
                                }
                            }
                            break;
                    }
                }
                size++;
            }
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, r.getUuid());
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {

    }

    @Override
    protected Resume doLoad(String uuid, File file) {
        try {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                String fullName = dis.readUTF();
                int contactSize = dis.readInt();
                Resume r = new Resume(uuid, fullName);
                for (int i = 0; i < contactSize; i++) {
                    r.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
                }
                int sectionSize = dis.readInt();
                for (int s = 0; s < sectionSize; s++) {
                    String sectionType = dis.readUTF();
                    switch (sectionType) {
                        case ("OBJECTIVE"):
                            r.addSection(OBJECTIVE, new TextSection(dis.readUTF()));
                            break;
                        case ("ACHIEVEMENT"):
                            int achievementSize = dis.readInt();
                            List<String> achievements = new LinkedList<>();
                            for (int i = 0; i < achievementSize; i++) {
                                achievements.add(dis.readUTF());
                                r.addSection(ACHIEVEMENT, new MultiTextSection(achievements));
                            }
                            break;
                        case ("QUALIFICATIONS"):
                            int qualificationSize = dis.readInt();
                            List<String> qualifications = new LinkedList<>();
                            for (int i = 0; i < qualificationSize; i++) {
                                qualifications.add(dis.readUTF());
                            }
                            r.addSection(QUALIFICATIONS, new MultiTextSection(qualifications));
                            break;
                        case ("EXPERIENCE"):
                            int organisationsSize = dis.readInt();
                            List<Organization> experiences = new LinkedList<>();
                            for (int i = 0; i < organisationsSize; i++) {
                                Link link = new Link(dis.readUTF(), dis.readUTF());
                                int positionSize = dis.readInt();
                                List<Position> positions = new LinkedList<>();
                                for (int a = 0; a < positionSize; a++) {
                                    positions.add(new Position(dis.readInt(), Month.of(dis.readInt()), dis.readInt(), Month.of(dis.readInt()), dis.readUTF(), dis.readUTF()));
                                }
                                Organization org = new Organization(link, positions);
                                experiences.add(org);
                            }
                            r.addSection(EXPERIENCE, new OrganizationSection(experiences));
                            break;
                        case ("EDUCATION"):
                            int educationSize = dis.readInt();
                            List<Organization> educations = new LinkedList<>();
                            for (int i = 0; i < educationSize; i++) {
                                Link link = new Link(dis.readUTF(), dis.readUTF());
                                int positionSize = dis.readInt();
                                List<Position> positions = new LinkedList<>();
                                for (int a = 0; a < positionSize; a++) {
                                    positions.add(new Position(dis.readInt(), Month.of(dis.readInt()), dis.readInt(), Month.of(dis.readInt()), dis.readUTF(), dis.readUTF()));
                                }
                                Organization org = new Organization(link, positions);
                                educations.add(org);
                            }
                            r.addSection(EDUCATION, new OrganizationSection(educations));
                            break;

                    }
                }
                return r;
            }
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, uuid);
        }
    }

    @Override
    protected void doDelete(String uuid, File file) {
        if (!file.delete()) {
            throw new WebAppException(ExceptionType.IO_ERROR, uuid);
        }
        size--;
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> sortedList = new LinkedList<>();
        for (String uuid : directory.list()) {
            sortedList.add(load(uuid));
            System.out.println(uuid);
        }
        return sortedList;
    }

    @Override
    public int size() {
        return size;
    }
}
