package org.example;

import org.example.domain.Proba;
import org.example.API.RestTest;
import org.example.API.ServiceException;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RestTest restTest = new RestTest();

        try {
            System.out.println(Arrays.toString(restTest.getAll()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(restTest.getById("2"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(restTest.create(new Proba("bras",100)));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            Integer id = 3;
            Proba proba = new Proba("bras",100);
            restTest.update(id,proba);

            System.out.println(Arrays.toString(restTest.getAll()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            restTest.delete("1");
            System.out.println(Arrays.toString(restTest.getAll()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}