import java.util.concurrent.atomic.AtomicLong;

public class InterruptedDemo {
    // interrupt дает возможность уверенно и безопасно остановить потоки после выполнения, чтобы не блочить приложение, например освободить ресурс для дальнейшего использования
    private static final AtomicLong counter = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                counter.incrementAndGet();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e)  {
                    System.err.println(e.getLocalizedMessage());
                    break;
                }
            }
        });

        th.start();

        Thread.sleep(500);
        // прервем поток
        th.interrupt();
        System.out.println(counter);
    }
}
