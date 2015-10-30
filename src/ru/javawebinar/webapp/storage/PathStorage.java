package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.exceptions.ExceptionType;
import ru.javawebinar.webapp.exceptions.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 23.10.2015.
 */
abstract public class PathStorage extends AbstractStorage<Path> {
    protected final Path directory;

    public PathStorage(String path) {
        directory = Paths.get(path);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException("'" + path + "' is not directory or is not writable");
        }
    }

    protected abstract void write(Resume r, OutputStream os) throws IOException;

    protected abstract Resume read(InputStream is) throws IOException;

    @Override
    protected void doClear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, null, e);
        }
    }

    @Override
    protected Path getContext(String fileName) {
        return directory.resolve(fileName);
    }

    @Override
    protected boolean exist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, r.getUuid(), e);
        }
        write(r, path);
    }

    protected void write(Resume r, Path path) {
        try {
            write(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, r.getUuid(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        write(r, path);
    }

    @Override
    protected Resume doLoad(Path path) {
        try {
            return read(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        try {
            return Files.list(directory).map(this::doLoad).collect(Collectors.<Resume>toList());
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, null, e);
        }
    }

    @Override
    public int size() {
        try {
            return Files.list(directory).collect(Collectors.counting()).intValue();
        } catch (IOException e) {
            throw new WebAppException(ExceptionType.IO_ERROR, null, e);
        }
    }
}
