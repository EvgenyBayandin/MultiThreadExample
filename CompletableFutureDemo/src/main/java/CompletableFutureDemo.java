import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        // Run Async ничего не возвращаем
        CompletableFuture<Void> task = CompletableFuture.runAsync(() -> System.out.println("Run Async"));

        // возвращаем строки
        CompletableFuture<String> supplier1 = CompletableFuture.supplyAsync(() -> "return parameter 1");
        CompletableFuture<String> supplier2  = CompletableFuture.supplyAsync(()  ->  "return parameter 2");

        // выполняет функцию с потоками
        supplier1
                .thenCombine(supplier2, (s1,s2) -> s1 + "\t" + s2)
                .thenApply(t -> "t: " + t)
                .thenAccept(res -> System.out.println("res: " + res));
    }
}
