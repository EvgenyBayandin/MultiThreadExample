package ru.webdev.asyncdemo.controller;

import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webdev.asyncdemo.service.AsyncTaskService;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Autowired
    private ExecutorService executorService;

    @GetMapping("/default")
    public ResponseEntity<String> runAsyncTask() {
        asyncTaskService.printTestCount();
        return new ResponseEntity<>("start process with default executor ...", HttpStatus.OK);
    }

    @GetMapping("/manual")
    public ResponseEntity<String> runManualAsyncTask()  {
        executorService.submit(() -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " "  +  i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e)  {
                    throw new RuntimeException(e);
                }
            }
        });
        return new ResponseEntity<>("start process with manual executor ...", HttpStatus.OK);
    }

}
