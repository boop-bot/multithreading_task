package edu.epam.multithreading.parser.impl;

import edu.epam.multithreading.entity.CargoType;
import edu.epam.multithreading.entity.Truck;
import edu.epam.multithreading.parser.TruckParser;
import edu.epam.multithreading.reader.impl.TruckReaderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TruckParserImpl implements TruckParser {
    private static final Logger logger = LogManager.getLogger(TruckParserImpl.class);
    private static final int INDEX_OF_CAPACITY = 0;
    private static final int INDEX_OF_CARGO_TYPE = 1;
    private static final int INDEX_OF_LOAD = 2;
    private static final int TRUCK_DATA_WITHOUT_LOAD = 2;

    @Override
    public Truck parse(String data) {
        String[] truckData = data.split(";");
        int capacity = Integer.parseInt(truckData[INDEX_OF_CAPACITY]);
        CargoType cargoType = CargoType.valueOf(truckData[INDEX_OF_CARGO_TYPE]);
        Truck truck;
        if (truckData.length == TRUCK_DATA_WITHOUT_LOAD) {
            truck = new Truck(capacity, cargoType);
        } else {
            int load = Integer.parseInt(truckData[INDEX_OF_LOAD]);
            truck = new Truck(capacity, load, cargoType);
        }
        logger.info("Truck parsed: {}", truck);
        return truck;
    }
}
