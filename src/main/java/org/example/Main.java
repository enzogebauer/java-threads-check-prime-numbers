package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] threadNumbers = {1, 5, 10}; // Apenas os números das threads desejadas
        String inputFile = "/Users/enzogebauer/IdeaProjects/CheckPrimeNumberJavaThreads/src/main/java/org/example/input.txt";
        String outputFilePrefix = "output_parallel";
        String execTimeFile = "execTime.txt";

        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            List<Integer> numbers = readNumbersFromFile(inputFile);
            for (int threadNumber : threadNumbers) {
                long startTime = System.currentTimeMillis();
                List<Integer> primes = findPrimesParallel(numbers, threadNumber);
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;

                writePrimesToFile(primes, numbers, outputFilePrefix + threadNumber + "_threads.txt");
                writeExecutionInfoToFile(threadNumber, executionTime, execTimeFile);

                dataset.addValue(executionTime, "Execution Time", String.valueOf(threadNumber));
            }

            // Create chart
            JFreeChart chart = ChartFactory.createLineChart(
                    "Performance Comparison",
                    "Number of Threads",
                    "Execution Time (ms)",
                    dataset
            );

            // Display chart
            ChartPanel chartPanel = new ChartPanel(chart);
            JFrame frame = new JFrame("Performance Comparison");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readNumbersFromFile(String fileName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        }
        return numbers;
    }

    private static List<Integer> findPrimesParallel(List<Integer> numbers, int numThreads) {
        List<Integer> primes = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        int chunkSize = numbers.size() / numThreads;
        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? numbers.size() : (i + 1) * chunkSize;
            Thread thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (isPrime(numbers.get(j))) {
                        synchronized (primes) {
                            primes.add(numbers.get(j));
                        }
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return primes;
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void writePrimesToFile(List<Integer> primes, List<Integer> numbers, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int num : numbers) {
                if (primes.contains(num)) {
                    writer.write(String.valueOf(num));
                    writer.newLine();
                }
            }
        }
    }

    private static void writeExecutionInfoToFile(int threadNumber, long executionTime,  String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(threadNumber + " Tempo de execuçāo em ms: " + executionTime);
            writer.newLine();
        }
    }
}
