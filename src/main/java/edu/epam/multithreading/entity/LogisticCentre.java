package edu.epam.multithreading.entity;

import edu.epam.multithreading.exception.ResourceException;
import edu.epam.multithreading.util.comparator.TruckComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticCentre {
    private static final int TERMINALS_AMOUNT = 5;
    private List<Terminal> terminals = new ArrayList<>();
    private Queue<Truck> trucksOutsideCenter = new PriorityQueue<>(new TruckComparator());
    private static final Semaphore semaphore = new Semaphore(TERMINALS_AMOUNT,true);
    private Lock lock = new ReentrantLock();
    private static final LogisticCentre instance = new LogisticCentre();
    private static final Logger logger = LogManager.getLogger(LogisticCentre.class);

    private LogisticCentre() {
        for (int i = 0; i < TERMINALS_AMOUNT; i++) {
            terminals.add(new Terminal());
        }
    }

    public static LogisticCentre getInstance() {
        return instance;
    }

    public Terminal getTerminal(Truck truck) throws ResourceException {
        Terminal terminal;
        try {
            semaphore.acquire();
            lock.lock();
            Optional<Terminal> optional = terminals.stream().filter(o -> !o.isBusy()).findAny();
            terminal = optional.get();
            trucksOutsideCenter.remove(truck);
            terminal.setBusy(true);
            logger.info(truck + " got "+ terminal);
            return terminal;
        } catch (InterruptedException e) {
            logger.error(e);
        }finally {
            lock.unlock();
        }
        throw new ResourceException();
    }

    public void returnTerminal(Truck truck, Terminal terminal) {
        try {
            lock.lock();
            terminal.setBusy(false);
            logger.info(truck + " left the " + terminal);
        } finally {
            lock.unlock();
        }
        semaphore.release();
    }

    public void moveTruckNearCenter(Truck truck){
        try {
            lock.lock();
            trucksOutsideCenter.add(truck);
            logger.info(truck + " stays in the queue");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            logger.error(e);
        } finally {
            lock.unlock();
        }
    }
}
