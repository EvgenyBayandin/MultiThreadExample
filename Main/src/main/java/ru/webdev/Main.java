package ru.webdev;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // наш поток
        MyThread myThread = new MyThread();
        myThread.start();

        // создаем основной поток
        Runnable runnable = new Runnable() {

            // переопределяем метод run()
            @Override
            public void run() {

                // пишем свою логику для потока вывода чисел от 10 до 20 включительно .rangeClosed(10,20)
                IntStream.rangeClosed(10, 20).forEach(System.out::print); // 1011121314151617181920
            }
        };

        // запускаем поток
        new Thread(runnable).start();

    }
}

class MyThread extends Thread {

    // переопределяем метод run()
    @Override
    public void run() {

        // пишем свою логику для потока вывода чисел от 0 до 10 .range(1, 10)
        IntStream.range(0, 10).forEach(System.out::print); // 0123456789
    }
}