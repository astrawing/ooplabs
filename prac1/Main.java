public class Main {
    public static void main(String[] args) {
        new NewThread();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Главный поток: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Главный. поток прерван!");
        }
        System.out.println("Главный поток завершён!");
    }
}

class NewThread implements Runnable {
    Thread t;

    NewThread() {
        t = new Thread(this, "Демонстрационный поток");
        System.out.println("Поток создан");
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Поток NewThread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток NewThread прерван!");
        }
        System.out.println("Работа потока NewThread завершена");
    }
}