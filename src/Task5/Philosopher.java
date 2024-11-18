package Task5;

public class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private int timesToEat = 3;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (timesToEat > 0) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((int) (Math.random() * 100));
    }

    private void eat() throws InterruptedException {
        pickUpForks();
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((int) (Math.random() * 100));
        putDownForks();
        timesToEat--;
    }

    private void pickUpForks() throws InterruptedException {
        leftFork.pickUp();
        System.out.println("Philosopher " + id + " picked up left fork.");
        rightFork.pickUp();
        System.out.println("Philosopher " + id + " picked up right fork.");
    }

    private void putDownForks() {
        leftFork.putDown();
        System.out.println("Philosopher " + id + " put down left fork.");
        rightFork.putDown();
        System.out.println("Philosopher " + id + " put down right fork.");
    }
}

