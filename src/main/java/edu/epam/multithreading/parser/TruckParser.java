package edu.epam.multithreading.parser;

import edu.epam.multithreading.entity.Truck;

public interface TruckParser {
    Truck parse(String data);
}
