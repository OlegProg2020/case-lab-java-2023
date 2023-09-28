package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                String path = in.next();
                File file = new File(path);

                String operation = in.next();
                switch (operation) {
                    case "-create" -> {
                        try {
                            if (file.createNewFile()) {
                                System.out.println("Файл создан.");
                            } else {
                                System.out.println("Файл уже существует.");
                            }
                        } catch (IOException e) {
                            System.out.println("При создании файла возникла ошибка. Повторите попытку.");
                        } catch (SecurityException e) {
                            System.out.println("Доступ для записи запрещен.");
                        }
                    }
                    case "-read" -> {
                        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                            fileReader.lines().forEachOrdered(System.out::println);
                        } catch (FileNotFoundException e) {
                            System.out.println("Файл не найден.");
                        } catch (IOException e) {
                            System.out.println("При чтении файла возникла ошибка. Повторите попытку.");
                        }
                    }
                    case "-remove" -> {
                        try {
                            if (file.delete()) {
                                System.out.println("Файл удален.");
                            } else {
                                System.out.println("Возникла ошибка. Файл не удален. " +
                                        "Возможно, вы пытаетесь удалить не пустую директорию.");
                            }
                        } catch (SecurityException e) {
                            System.out.println("Доступ для удаления запрещен.");
                        }
                    }
                    case "-write" -> {
                        String text = in.nextLine();
                        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
                            fileWriter.write(text);
                            System.out.println("Текст записан.");
                        } catch (IOException e) {
                            System.out.println("Файл существует, но является каталогом, а не обычным файлом, " +
                                    "или не существует, но не может быть создан, или не может быть открыт " +
                                    "по любой другой причине.");
                        }
                    }
                }
            }
        }
    }
}