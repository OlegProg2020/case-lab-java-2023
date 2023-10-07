package org.example;

/*
Реализовать механизм периодического асинхронного выполнения задач.
Т.е. механизм, который, не останавливая работу основной программы, раз в 10 секунд
делает следующие действия:
1. пишет в консоль "Асинхронный привет!"
2. ждет (sleep) 5 секунд
3. пишет в консоль "Асинхронный пока!"
Основная программа при этом должна каждую секунду писать в консоль "Работает
основная программа".
* */

public class Main {
    public static void main(String[] args) {
        Runnable function = () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + ": Асинхронный привет!");
                    Thread.sleep(5_000L);
                    System.out.println(Thread.currentThread().getName() + ": Асинхронный пока!");
                    Thread.sleep(5_000L);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        };

        Thread child = new Thread(function, "child");
        child.start();

        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ": Работает основная программа");
                Thread.sleep(1_000L);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}