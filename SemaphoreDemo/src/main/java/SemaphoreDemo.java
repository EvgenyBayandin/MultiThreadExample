import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException{

        // Create the semaphore with the number of permits to isissue
        // разрешим два потока (permits: 2) и установим ключ справедливости (fair: true) в true что бы ресурс был доступен не одному и тому же потоку а распределялся между всеми (по умолчанию ключ fair: false)
        Semaphore semaphore = new Semaphore(2, true);

        /*
        * Занимаем семафор
        */
        semaphore.acquire();
        System.out.println("+++++++++++++");

        /*
        * Освобождаем семафор
        */
        semaphore.release();

        Runnable task = ()  -> {
            try {
                semaphore.acquire();
                System.out.println("Executing task by thread " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e)  {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };

        List<Thread> threads = List.of(new Thread(task), new Thread(task), new Thread(task));
        threads.forEach(Thread::start);
        threads.forEach(SemaphoreDemo::joinThread);

    }

    private static void joinThread(Thread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
