package edu.epam.multithreading.reader;

import java.io.IOException;
import java.util.List;

public interface TruckReader {
    List<String> read(String path) throws IOException;
}
