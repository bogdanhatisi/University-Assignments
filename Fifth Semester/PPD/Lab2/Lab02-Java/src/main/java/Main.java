import java.io.*;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main {
    private static int N;
    private static int M;
    private static int n;
    private static int m;
    private static int p;
    private static int[][] matrix;
    private static int[][] kernel;

    private static CyclicBarrier barrier;

    // Read input data from a file
    public static void read(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            // Read matrix dimensions
            N = Integer.parseInt(myReader.nextLine());
            M = Integer.parseInt(myReader.nextLine());

            // Initialize matrix
            matrix = new int[N][M];

            // Read matrix values
            for (int i = 0; i < N; i++) {
                String data = myReader.nextLine();
                String[] line = data.split(" ");
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }

            // Read kernel dimensions
            n = Integer.parseInt(myReader.nextLine());
            m = Integer.parseInt(myReader.nextLine());

            // Initialize kernel
            kernel = new int[n][m];

            // Read kernel values
            for (int i = 0; i < n; i++) {
                String data = myReader.nextLine();
                String[] line = data.split(" ");
                for (int j = 0; j < m; j++) {
                    kernel[i][j] = Integer.parseInt(line[j]);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Write matrix to a file
    public static void write(String path, int[][] matrix) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int[] elem : matrix) {
                for (int i : elem) {
                    bw.write(i + " ");
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) throws Exception {
        read("input.txt");
        p = Integer.parseInt(args[0]);

        // Initialize cyclic barrier if needed
        if (p != 0) {
            barrier = new CyclicBarrier(p);
        }

        // Perform convolution based on the number of threads
        if (p == 0)
            sequentialConvolution();
        else
            parallelConvolution();

        // Write the result to the output file
        write("output.txt", matrix);

        // If sequential, write to a validation file; if parallel, check compliance with the validation file
        if (p == 0) {
            write("valid.txt", matrix);
        } else {
            checkCompliance("output.txt", "valid.txt");
        }
    }

    // Check compliance between two files
    private static void checkCompliance(String pathTest, String pathValid) throws Exception {
        File objTest = new File(pathTest);
        Scanner readerTest = new Scanner(objTest);

        File objValid = new File(pathValid);
        Scanner readerValid = new Scanner(objValid);

        // Compare each line of the test and validation files
        while (readerTest.hasNextLine() && readerValid.hasNextLine()) {
            String test = readerTest.nextLine();
            String valid = readerValid.nextLine();

            if (!valid.equals(test)) {
                throw new Exception("Invalid output");
            }
        }

        // If one file has more lines than the other, throw an exception
        if (readerTest.hasNextLine() || readerValid.hasNextLine()) {
            throw new Exception("Invalid output");
        }
    }

    // Perform sequential convolution
    public static void sequentialConvolution() {
        long startTime = System.nanoTime();

        int[] previousLine = new int[M];
        int[] currentLine = new int[M];

        // Initialize previous and current lines with the first row of the matrix
        System.arraycopy(matrix[0], 0, previousLine, 0, M);
        System.arraycopy(matrix[0], 0, currentLine, 0, M);

        // Perform convolution for each row of the matrix
        for (int i = 0; i < N; i++) {
            int[] buffer = new int[M];
            for (int j = 0; j < M; j++) {
                int output;
                output = compute(previousLine, j, 0) + compute(currentLine, j, 1) + compute(matrix[min(N - 1, i + 1)], j, 2);

                buffer[j] = output;
            }

            // Update the matrix with the computed values
            System.arraycopy(buffer, 0, matrix[i], 0, M);
            System.arraycopy(currentLine, 0, previousLine, 0, currentLine.length);
            System.arraycopy(matrix[min(N - 1, i + 1)], 0, currentLine, 0, currentLine.length);
        }

        long endTime = System.nanoTime();
        System.out.println((double)(endTime - startTime) / 1E6);
    }

    // Perform parallel convolution using multiple threads
    public static void parallelConvolution() throws InterruptedException {
        Thread[] t = new Worker[p];

        int start, end = 0;
        int chunk = N / p;
        int rest = N % p;

        long startTime = System.nanoTime();

        // Create and start threads
        for (int i = 0; i < t.length; i++) {
            start = end;
            end = start + chunk;
            if (rest > 0) {
                end++;
                rest--;
            }
            t[i] = new Worker(start, end);
            t[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : t) {
            thread.join();
        }

        long stopTime = System.nanoTime();

        System.out.println((double)(stopTime - startTime) / 1E6);
    }

    // Worker class for parallel convolution
    public static class Worker extends Thread {
        int start, end;

        public Worker(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            int[] previousLine = new int[M];
            int[] currentLine = new int[M];

            // Initialize previous and current lines with the appropriate rows of the matrix
            System.arraycopy(matrix[Math.max(start - 1, 0)], 0, previousLine, 0, M);
            System.arraycopy(matrix[start], 0, currentLine, 0, M);

            int[] bufferUp = new int[M];
            int[] bufferDown = new int[M];

            // Perform convolution for the assigned range of rows
            for (int i = start; i < end; i++) {
                for (int j = 0; j < M; j++) {
                    int output;
                    output = compute(previousLine, j, 0) + compute(currentLine, j, 1) + compute(matrix[min(N - 1, i + 1)], j, 2);

                    if (i == start) {
                        bufferUp[j] = output;
                    } else if (i == end - 1) {
                        bufferDown[j] = output;
                    } else {
                        matrix[i][j] = output;
                    }
                }

                // Update previous and current lines for the next iteration
                System.arraycopy(currentLine, 0, previousLine, 0, currentLine.length);
                System.arraycopy(matrix[min(N - 1, i + 1)], 0, currentLine, 0, currentLine.length);
            }

            // Synchronize threads using the cyclic barrier
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException ignored) {}

            // Update the matrix with the buffered values
            System.arraycopy(bufferUp, 0, matrix[start], 0, M);
            System.arraycopy(bufferDown, 0, matrix[end - 1], 0, M);
        }
    }

    // Compute the convolution value for a given position in the matrix
    private static int compute(int[] values, int j, int kernelRow) {
        return values[max(j - 1, 0)] * kernel[kernelRow][0] + values[j] * kernel[kernelRow][1] + values[min(M - 1, j + 1)] * kernel[kernelRow][2];
    }
}
