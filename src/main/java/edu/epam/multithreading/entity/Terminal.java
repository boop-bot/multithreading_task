package edu.epam.multithreading.entity;

import edu.epam.multithreading.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Terminal {
    private static final Logger logger = LogManager.getLogger(Terminal.class);
    private long id;
    private static final int CARGO_TO_MOVE = 5;

    public Terminal() {
        this.id = IdGenerator.generateTerminalId();
    }

    public long getId() {
        return id;
    }

    public void serve(Truck truck) {
        if (truck.getLoad() > 0) {
            while (truck.getLoad() > 0) {
                truck.unload(CARGO_TO_MOVE);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
            logger.info(truck + " was unloaded on " + this.toString());
        } else {
            while (truck.getLoad() < truck.getCapacity()){
                truck.load(CARGO_TO_MOVE);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
            logger.info(truck + " was loaded on " + this.toString());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal terminal = (Terminal) o;

        return id == terminal.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}