import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

    // Create executors
    // Задачи передаются в ExExecutorService, где помещаются в очередь и рараспределяются в пул потоков
    private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Submit tasks
        executor.submit(() -> System.out.println(Thread.currentThread().getName()));

        // stop the executor immediately
        // executor.shutdown();

        // Wait for the executor to stop
        boolean terminated = executor.awaitTermination(10, TimeUnit.SECONDS);

        if(terminated){
            System.out.println("The executor was successfully stopped");
        } else {
            System.out.println("Timeout elapsed before executor was stopped");
        }

    }
}
