import java.util.stream.IntStream;


class VolatileExample {

    // volatile ключевое слово, которое определяет хранить значение в оперативной памяти, но время обращения к нему увеличится в отличие от кэша
    volatile int x = 0;

    public static final int THREAD_COUNT = 100;

    public void increaseX(){
        x++;
    }

    public static void main(String[] args) {
        final VolatileExample counter = new VolatileExample();
        IntStream.range(0, THREAD_COUNT).forEach(i -> new Thread(counter::increaseX).start());
        while (counter.x < THREAD_COUNT) {
        } // busy wait
        System.out.println(counter.x);
    }
}

public class VolatileDemo  {

    //synchronized - состояние гонки, когда потоки борются за один ресурс
    private static  volatile int count = 2_000_000;

    public static void main(String[] args) throws InterruptedException {
        Runnable decrement = () -> IntStream.range(0, 2_000_000).forEach(i  -> decrement());
        Runnable increment  = ()  -> IntStream.range(0,  2_000_000).forEach(i   -> increment());

        Thread decrementThread  =  new Thread(decrement);
        Thread incrementThread   =  new Thread(increment);
        decrementThread.start();
        incrementThread.start();
        decrementThread.join();
        incrementThread.join();

        System.out.println(count);
    }
    // без synchronized число операий не будет равняться 2_000_000
    // private static void decrement()  { count--; }
    private static synchronized void decrement()  { count--; }

    private static synchronized void increment()  { count++; }

}