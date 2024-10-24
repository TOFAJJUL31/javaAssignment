package CorruptedList;

public class tester {
    public static void main(String[] args) {
        CorruptedList corruptedList = new CorruptedList();

        // Using lambda expressions for Runnable
        Runnable taskAdder = () -> {
            for (int i = 0; i < 1000; i++) {
                corruptedList.adder(i);
            }
        };

        Runnable taskRemove = () -> {
            for (int i = 0; i < 1000; i++) {
                corruptedList.remover();
            }
        };

        Thread thread1 = new Thread(taskAdder);
        Thread thread2 = new Thread(taskRemove);
        Thread thread3 = new Thread(taskAdder);
        Thread thread4 = new Thread(taskRemove);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        corruptedList.printer();
    }
}
