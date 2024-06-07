import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CreateThreadsDemo {
    public static void main(String[] args) {

        // Create a new thread
        Runnable worker = ()  -> IntStream.rangeClosed(1, 10).forEach(CreateThreadsDemo::sleepThread);
        List<Thread> threads = new ArrayList<>();
        IntStream.rangeClosed(1,  10).forEach(i -> threads.add(new Thread(worker)));
        threads.forEach(Thread::start);
        threads.forEach(CreateThreadsDemo::joinThread);
        System.out.println("Finish");

    }

    private static void sleepThread(int i)  {
        System.out.print(Thread.currentThread().getName()  + " " + i + "\n");
        try {
            // поток засыпает на 500 мс
//            Thread.sleep(500);
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e)  {
            throw new RuntimeException(e);
        }
    }

    private static void joinThread(Thread thread)   {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
