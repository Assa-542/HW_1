package Task5;

public class Fork {
    private boolean inUse = false;

    public synchronized void pickUp() throws InterruptedException {
        while (inUse) {
            wait();
        }
        inUse = true;
    }

    public synchronized void putDown() {
        inUse = false;
        notifyAll();
    }
}

