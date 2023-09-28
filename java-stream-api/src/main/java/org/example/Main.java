package org.example;

import java.util.*;
import java.util.stream.Stream;

/*
 * Задача - найти разработчиков с уникальными языками программирования, используя Stream API.
 * Для данного примера ожидаемый результат [Наташа, Элла].
 * */
public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        Stream<Developer> developerStream = Stream.of(dev1, dev2, dev3);

        Map<String, Integer> languageRepeats = new HashMap<>();
        developerStream.flatMap(developer -> developer.getLanguages().stream())
                .forEach(language -> languageRepeats.compute(language, (k, v) -> (v == null) ? 1 : v + 1));

        Set<String> uniqueLanguages = new HashSet<>();
        languageRepeats.forEach((key, value) -> {
            if (value.equals(1)) {
                uniqueLanguages.add(key);
            }
        });

        Stream.of(dev1, dev2, dev3).filter(developer ->
                        uniqueLanguages.stream().anyMatch(language -> developer.getLanguages().contains(language)))
                .forEach(developer -> System.out.println(developer.getName()));
    }
}