package edu.epam.multithreading.entity;

import edu.epam.multithreading.util.comparator.TruckComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LogisticCentre {
    private static final int TERMINALS_AMOUNT = 5;
    private List<Terminal> terminals = new ArrayList<>();
    private Queue<Truck> trucksOutsideBase = new PriorityQueue<>(new TruckComparator());
    private static final LogisticCentre instance = new LogisticCentre();

    private LogisticCentre() {
        for (int i = 0; i < TERMINALS_AMOUNT; i++) {
            terminals.add(new Terminal());
        }
    }

    public static LogisticCentre getInstance() {
        return instance;
    }

    public Terminal takeTerminal(Truck truck) {
        Terminal terminal = new Terminal();

        return terminal;
    }

    public void releaseTerminal() {

    }
}
