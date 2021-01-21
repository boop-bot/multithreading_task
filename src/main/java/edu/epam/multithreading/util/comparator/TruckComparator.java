package edu.epam.multithreading.util.comparator;

import edu.epam.multithreading.entity.CargoType;
import edu.epam.multithreading.entity.Truck;

import java.util.Comparator;

public class TruckComparator implements Comparator<Truck> {
    @Override
    public int compare(Truck o1, Truck o2) {
        if (CargoType.PERISHABLE.equals(o1.getCargoType()) && CargoType.STANDARD.equals(o2.getCargoType())) {
            return -1;
        } else if (CargoType.STANDARD.equals(o1.getCargoType()) && CargoType.PERISHABLE.equals(o2.getCargoType())) {
            return 1;
        } else {
            return 0;
        }
    }
}
