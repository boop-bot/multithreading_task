package edu.epam.multithreading.reader.impl;

import edu.epam.multithreading.reader.TruckReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TruckReaderImpl implements TruckReader {
    private static final Logger logger = LogManager.getLogger(TruckReaderImpl.class);
    @Override
    public List<String> read(String path) throws IOException{
        List<String> lines;
        try (Stream<String> stream = Files.lines(Path.of(path))){
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("File reading error ");
            throw new IOException("File reading error");
        }
        logger.info("Lines were read");
        return lines;
    }
}
