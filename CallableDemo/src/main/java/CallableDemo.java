import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException{

        //ExecutorService executor = Executors.newSingleThreadExecutor(); // выполнение за 6 секунд в 1 поток
        ExecutorService executor = Executors.newFixedThreadPool(2); // выполнение за 3 секунды в  2 потока

        calculateMeasure(executor);
    }

    private static void calculateMeasure(ExecutorService executor) throws InterruptedException, ExecutionException  {
        long start = System.currentTimeMillis();
        calculate(executor);
        long stop  = System.currentTimeMillis();
        System.out.println("calculate time : "  + (stop-start));
    }

    private static void calculate(ExecutorService executor) throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            TimeUnit.SECONDS.sleep(3);
            return new java.util.Random().nextInt(10);
        };

        // запуск выполнения
        Future<Integer> future1= executor.submit(task);
        Future<Integer> future2= executor.submit(task);

        // проверим выполнено ли выполнение
        //System.out.println(future1.isDone());

        System.out.println(future1.get() + " : " + future2.get());

        executor.shutdown();
    }
}
