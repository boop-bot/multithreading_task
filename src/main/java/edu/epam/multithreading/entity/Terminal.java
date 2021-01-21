package edu.epam.multithreading.entity;

import edu.epam.multithreading.util.IdGenerator;

import java.util.concurrent.TimeUnit;

public class Terminal {
    private long id;
    private boolean isBusy;
    private static final int CARGO_TO_MOVE = 5;

    public Terminal() {
        this.id = IdGenerator.generateTerminalId();
    }

    public long getId() {
        return id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void serve(Truck truck) {
        if (truck.getLoad() > 0) {
            while (truck.getLoad() > 0) {
                truck.unload(CARGO_TO_MOVE);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        } else {
            while (truck.getLoad() < truck.getCapacity()){
                truck.load(CARGO_TO_MOVE);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("id=").append(id);
        sb.append(", isBusy=").append(isBusy);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal terminal = (Terminal) o;

        if (id != terminal.id) return false;
        return isBusy == terminal.isBusy;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isBusy ? 1 : 0);
        return result;
    }
}