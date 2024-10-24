package SafeQueue;

public class SafeQueueTest {
    public static void main(String[] args) {
        SafeQueue queue = new SafeQueue();

        // Using lambda expressions for Runnable
        Runnable adder = () -> {
            for (int i = 1; i <= 10; i++) {
                queue.enqueue(i);
            }
        };

        Runnable remover = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.dequeue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread producerThread = new Thread(adder);
        Thread consumerThread = new Thread(remover);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.printer();
    }
}
