import java.io.*;
import java.util.Scanner;

public class Main {
    private static int N, M, n, m, p, offset;
    private static int[][] matrix, kernel, finalMatrix;

    private static class Worker extends Thread {
        int start, end;

        public Worker(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            if (N > M) {
                for (int i = start; i < end; i++) {
                    for (int j = 0; j < M; j++) {
                        finalMatrix[i][j] = singlePixelConvolution(i, j);
                    }
                }
            } else {
                for (int i = 0; i < N; i++) {
                    for (int j = start; j < end; j++) {
                        finalMatrix[i][j] = singlePixelConvolution(i, j);
                    }
                }
            }
        }
    }

    public static void read(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            if (myReader.hasNextLine()) {
                N = Integer.parseInt(myReader.nextLine());
                M = Integer.parseInt(myReader.nextLine());
            }
            matrix = new int[N][M];
            readMatrix(myReader, matrix, N, M);

            if (myReader.hasNextLine()) {
                n = Integer.parseInt(myReader.nextLine());
                m = Integer.parseInt(myReader.nextLine());
            }
            kernel = new int[n][m];
            readMatrix(myReader, kernel, n, m);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void readMatrix(Scanner scanner, int[][] array, int rows, int cols) {
        if (scanner.hasNextLine()) {
            for (int i = 0; i < rows; i++) {
                String data = scanner.nextLine();
                String[] line = data.split(" ");
                for (int j = 0; j < cols; j++) {
                    array[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
    }

    public static void write(String path, int[][] matrix) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
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

    public static void main(String[] args) throws Exception {
        read("input.txt");
        finalMatrix = new int[N][M];
        p = Integer.parseInt(args[0]);
        offset = (n - 1) / 2;
        System.out.println(p);

        if (p == 0)
            sequentialConvolution();
        else
            parallelConvolution();

        write("output.txt", finalMatrix);

        if (p == 0) {
            write("valid.txt", finalMatrix);
        } else {
            checkCompliance("output.txt", "valid.txt");
        }
    }

    private static void checkCompliance(String pathTest, String pathValid) throws Exception {
        try (Scanner readerTest = new Scanner(new File(pathTest));
             Scanner readerValid = new Scanner(new File(pathValid))) {

            while (readerTest.hasNextLine() && readerValid.hasNextLine()) {
                String test = readerTest.nextLine();
                String valid = readerValid.nextLine();

                if (!valid.equals(test)) {
                    throw new Exception("Invalid output");
                }
            }

            if (readerTest.hasNextLine() || readerValid.hasNextLine()) {
                throw new Exception("Invalid output");
            }
        }
    }

    public static int singlePixelConvolution(int x, int y) {
        int output = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int ii = x - offset + i;
                int jj = y - offset + j;

                ii = Math.max(0, Math.min(ii, N - 1));
                jj = Math.max(0, Math.min(jj, M - 1));

                output += matrix[ii][jj] * kernel[i][j];
            }
        }
        return output;
    }

    public static void sequentialConvolution() {
        long startTime = System.nanoTime();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                finalMatrix[i][j] = singlePixelConvolution(i, j);
            }
        }

        long endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1E6);
    }

    public static void parallelConvolution() throws InterruptedException {
        Thread[] t = new Thread[p];

        int start, end = 0;
        int chunk = N / p;
        int rest = N % p;

        long startTime = System.nanoTime();

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

        for (Thread thread : t) {
            thread.join();
        }

        long stopTime = System.nanoTime();

        System.out.println((double) (stopTime - startTime) / 1E6);
    }
}
