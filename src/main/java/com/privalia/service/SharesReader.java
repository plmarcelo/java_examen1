package com.privalia.service;

import com.privalia.model.SharePrice;
import com.privalia.tool.ExtendedArrayList;
import com.privalia.tool.ExtendedList;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Log4j
public class SharesReader implements CsvReader<SharePrice> {

    private static final String fieldSeparator = ";";

    private boolean hasHeader = true;

    private String[] headers;

    @Override
    public List<SharePrice> read(String fileName) {
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile)) { //, StandardCharsets.US_ASCII
            return parseBuffer(bufferedReader);
        } catch (NoSuchFileException e) {
            log.error("Archivo no encontrado");
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    private List<SharePrice> parseBuffer(BufferedReader bufferedReader) throws IOException {
        ExtendedList<SharePrice> shares = new ExtendedArrayList<>();

        if (hasHeader) {
            headers = bufferedReader.readLine().split(fieldSeparator);
        }

        String line = bufferedReader.readLine();

        while (line != null) {

            shares.add(parseLine(line));

            line = bufferedReader.readLine();
        }

        return shares.reverse();
    }

    private SharePrice parseLine(String line) {
        List<String> attributes = Arrays.asList(line.split(fieldSeparator));

        try {
            return SharePrice.createFromStringList(attributes);
        } catch (ParseException e) {
            log.warn(String.format("Line %s cannot be parsed", line));
            return null;
        }
    }
}
