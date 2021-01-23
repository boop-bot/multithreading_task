package edu.epam.multithreading.entity;

import edu.epam.multithreading.exception.ResourceException;
import edu.epam.multithreading.parser.impl.TruckParserImpl;
import edu.epam.multithreading.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Truck implements Runnable {
    private static final Logger logger = LogManager.getLogger(Truck.class);
    private long id;
    private int capacity;
    private int load;
    private CargoType cargoType;

    public Truck(int capacity, CargoType cargoType) {
        this.id = IdGenerator.generateTruckId();
        this.capacity = capacity;
        this.cargoType = cargoType;
    }

    public Truck(int capacity, int load, CargoType cargoType) {
        this.id = IdGenerator.generateTruckId();
        this.capacity = capacity;
        this.load = load;
        this.cargoType = cargoType;
    }

    public long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLoad() {
        return load;
    }

    public void load(int load) {
        this.load += load;
    }

    public void unload(int load) {
        if (this.load >= load) {
            this.load -= load;
        } else {
            this.load = 0;
        }
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    @Override
    public void run() {
        LogisticCentre logisticCentre = LogisticCentre.getInstance();
        logisticCentre.moveTruckNearCenter(this);
        try {
            Terminal terminal;
            terminal = logisticCentre.getTerminal(this);
            terminal.serve(this);
            logisticCentre.returnTerminal(this,terminal);
        } catch (ResourceException e) {
            logger.error(e);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Truck{");
        sb.append("id=").append(id);
        sb.append(", capacity=").append(capacity);
        sb.append(", load=").append(load);
        sb.append(", cargoType=").append(cargoType);
        sb.append('}');
        return sb.toString();
    }


}
