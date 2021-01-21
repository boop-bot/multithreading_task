package edu.epam.multithreading.util;

public class IdGenerator {
    private static long truckId = 0;
    private static long terminalId = 0;

    public static long generateTruckId() {
        return ++truckId;
    }

    public static long generateTerminalId() {
        return ++terminalId;
    }
}
