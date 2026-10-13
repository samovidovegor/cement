package org.example;


import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleInteraction {

    private final Scanner scanner = new Scanner(System.in);

    public Path readInputPath() {
        System.out.print("Введите путь к файлу с заказами: ");
        return Path.of(scanner.nextLine().trim());
    }

    public Path readOutputPath() {
        System.out.print("Введите путь для сохранения результата: ");
        return Path.of(scanner.nextLine().trim());
    }
}

