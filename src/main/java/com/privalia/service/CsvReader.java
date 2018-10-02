package com.privalia.service;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public interface CsvReader<T> {
    public List<T> read(String fileName) throws IOException;
}
