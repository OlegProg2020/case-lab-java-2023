package org.example;

import com.github.javafaker.Faker;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        int count = 1000;
        int count2 = 100000;
        Collection<String> addresses = generateFakeAddresses(count);
        Collection<String> addresses2 = generateFakeAddresses(count2);

        ArrayList<String> arrayList = new ArrayList<>(addresses);
        System.out.println("ArrayList");
        System.out.println("Вставка:");
        measureAndDisplayElapsedTime(addresses2, arrayList::add);
        System.out.println("Поиск:");
        measureAndDisplayElapsedTime(addresses2, arrayList::contains);
        System.out.println("Удаление:");
        measureAndDisplayElapsedTime(addresses2, arrayList::remove);

        LinkedList<String> linkedList = new LinkedList<>(addresses);
        System.out.println("LinkedList");
        System.out.println("Вставка:");
        measureAndDisplayElapsedTime(addresses2, linkedList::add);
        System.out.println("Поиск:");
        measureAndDisplayElapsedTime(addresses2, linkedList::contains);
        System.out.println("Удаление:");
        measureAndDisplayElapsedTime(addresses2, linkedList::remove);

        HashSet<String> hashSet = new HashSet<>(addresses);
        System.out.println("HashSet");
        System.out.println("Вставка:");
        measureAndDisplayElapsedTime(addresses2, hashSet::add);
        System.out.println("Поиск:");
        measureAndDisplayElapsedTime(addresses2, hashSet::contains);
        System.out.println("Удаление:");
        measureAndDisplayElapsedTime(addresses2, hashSet::remove);

        TreeSet<String> treeSet = new TreeSet<>(addresses);
        System.out.println("TreeSet");
        System.out.println("Вставка:");
        measureAndDisplayElapsedTime(addresses2, treeSet::add);
        System.out.println("Поиск:");
        measureAndDisplayElapsedTime(addresses2, treeSet::contains);
        System.out.println("Удаление:");
        measureAndDisplayElapsedTime(addresses2, treeSet::remove);
    }

    private static void measureAndDisplayElapsedTime(
            Collection<String> collection, Consumer<? super String> operation) {
        Instant start = Instant.now();

        collection.forEach(operation);

        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println("Прошло времени, мс: " + elapsed);
    }

    private static Collection<String> generateFakeAddresses(int count) {
        Faker faker = new Faker();

        Collection<String> collection = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            collection.add(faker.address().fullAddress());
        }

        return collection;
    }
}

/*
ArrayList
Вставка:
Прошло времени, мс: 14
Поиск:
Прошло времени, мс: 45953
Удаление:
Прошло времени, мс: 1894
LinkedList
Вставка:
Прошло времени, мс: 9
Поиск:
Прошло времени, мс: 41388
Удаление:
Прошло времени, мс: 437
HashSet
Вставка:
Прошло времени, мс: 23
Поиск:
Прошло времени, мс: 8
Удаление:
Прошло времени, мс: 8
TreeSet
Вставка:
Прошло времени, мс: 117
Поиск:
Прошло времени, мс: 58
Удаление:
Прошло времени, мс: 107
* */