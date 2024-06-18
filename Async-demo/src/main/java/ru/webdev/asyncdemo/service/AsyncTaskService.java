package ru.webdev.asyncdemo.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class AsyncTaskService {

    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Bean(destroyMethod = "shutdown")
    public ExecutorService executorService() {
        return executorService;
    }

    @Async
    public void printTestCount() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName()  +  " "  +  i);
            try  {
                Thread.sleep(1000);
            } catch (InterruptedException e)  {
                throw new RuntimeException(e);
            }
        }
    }
}
