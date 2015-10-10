package main.ru.javawebinar.webapp;

import main.ru.javawebinar.webapp.model.Resume;

/**
 * GKislin
 * 09.10.2015. my version
 */
public class WebAppException extends RuntimeException {
    private Resume resume;
    private String uuid;

    public WebAppException(String message, String uuid) {
        this(message, null, uuid);
    }

    public WebAppException(String message, Resume resume) {
        this(message, resume, resume.getUuid());
    }

    private WebAppException(String message, Resume resume, String uuid) {
        super(message);
        this.resume = resume;
        this.uuid = uuid;
    }

    private WebAppException(Throwable cause, Resume resume, String uuid) {
        super(cause);
        this.resume = resume;
        this.uuid = uuid;
    }

    public Resume getResume() {
        return resume;
    }

    public String getUuid() {
        return uuid;
    }
}
