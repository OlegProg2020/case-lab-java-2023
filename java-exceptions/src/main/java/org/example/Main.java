package org.example;

import org.example.exception.SizeValidationException;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();
        try {
            for (int i = 0; i < 11; i++) {
                collection.add(i);

                if (collection.size() > 10) {
                    throw new SizeValidationException(10);
                }
            }
        } catch (SizeValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}