package edu.epam.multithreading.main;

import edu.epam.multithreading.entity.Truck;
import edu.epam.multithreading.parser.TruckParser;
import edu.epam.multithreading.parser.impl.TruckParserImpl;
import edu.epam.multithreading.reader.TruckReader;
import edu.epam.multithreading.reader.impl.TruckReaderImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        TruckReader truckReader = new TruckReaderImpl();
        //Starter starter = new TruckStarterImpl();
        List<String> truckData = truckReader.read("src/main/resources/file/data.txt");
        TruckParser parser = new TruckParserImpl();
        List<Truck> trucks = new ArrayList<>();
        for (String data : truckData) {
            trucks.add(parser.parse(data));
        }
        for(Truck truck : trucks){
            new Thread(truck).start();
        }
    }
}
