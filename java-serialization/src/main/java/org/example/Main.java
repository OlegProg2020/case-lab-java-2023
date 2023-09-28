package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Николай", 21),
                new Person("Кирилл", 5),
                new Person("Алексей", 13),
                new Person("Михаил", 73)
        );
        System.out.println("До сериализации:" + people);

        // Сериализация
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data"))) {
            objectOutputStream.writeObject(people);
        } catch (IOException e) {
            System.out.println("Произошла ошибка." + e.getMessage());
        }

        // Десериализация
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data"))) {
            people = (List<Person>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Произошла ошибка." + e.getMessage());
        }
        System.out.println("После десериализации: " + people);
    }
}