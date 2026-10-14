package org.example;

import org.example.console.ConsoleInteraction;

import java.io.IOException;
import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws IOException {
        ConsoleInteraction console = new ConsoleInteraction();

        Path inputPath = console.readInputPath();
        Path outputPath = console.readOutputPath();

        OrderApplication application = new OrderApplication();
        application.run(inputPath, outputPath);
    }
}