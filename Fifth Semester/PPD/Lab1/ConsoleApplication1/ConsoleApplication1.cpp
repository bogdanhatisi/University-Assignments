#include <iostream>
#include <fstream>
#include <vector>
#include <thread>
#include <chrono>

using namespace std;
using namespace std::chrono;

const int MAX_SIZE = 10000;
const int MAX_KERNEL_SIZE = 6;

struct Matrix {
    int rows, cols;
    vector<vector<int>> data;
};

void readMatrix(const string& path, Matrix& matrix) {
    ifstream fin(path);

    fin >> matrix.rows >> matrix.cols;
    matrix.data.resize(matrix.rows, vector<int>(matrix.cols));

    for (int i = 0; i < matrix.rows; ++i) {
        for (int j = 0; j < matrix.cols; ++j) {
            fin >> matrix.data[i][j];
        }
    }

    fin.close();
}

void writeMatrix(const string& path, const Matrix& matrix) {
    ofstream fout(path);

    for (int i = 0; i < matrix.rows; ++i) {
        for (int j = 0; j < matrix.cols; ++j) {
            fout << matrix.data[i][j] << " ";
        }
        fout << endl;
    }

    fout.close();
}

Matrix createEmptyMatrix(int rows, int cols) {
    return { rows, cols, vector<vector<int>>(rows, vector<int>(cols)) };
}

int singlePixelConvolution(const Matrix& input, const Matrix& kernel, int x, int y) {
    int output = 0;

    for (int i = 0; i < kernel.rows; ++i) {
        for (int j = 0; j < kernel.cols; ++j) {
            int ii = x - i + (kernel.rows - 1) / 2;
            int jj = y - j + (kernel.cols - 1) / 2;

            ii = max(0, min(ii, input.rows - 1));
            jj = max(0, min(jj, input.cols - 1));

            output += input.data[ii][jj] * kernel.data[i][j];
        }
    }

    return output;
}

void sequentialConvolution(const Matrix& input, const Matrix& kernel, Matrix& output) {
    auto startTime = high_resolution_clock::now();

    for (int i = 0; i < input.rows; ++i) {
        for (int j = 0; j < input.cols; ++j) {
            output.data[i][j] = singlePixelConvolution(input, kernel, i, j);
        }
    }

    auto endTime = high_resolution_clock::now();
    double difference = duration<double, milli>(endTime - startTime).count();
    cout << difference << endl;
}

void parallelConvolution(const Matrix& input, const Matrix& kernel, Matrix& output, int start, int end) {
    for (int i = start; i < end; ++i) {
        for (int j = 0; j < input.cols; ++j) {
            output.data[i][j] = singlePixelConvolution(input, kernel, i, j);
        }
    }
}

void parallelization(const Matrix& input, const Matrix& kernel, Matrix& output, int p) {
    vector<thread> threads;
    int chunk = input.rows / p;
    int rest = input.rows % p;

    auto startTime = high_resolution_clock::now();

    for (int i = 0, start = 0, end = 0; i < p; ++i) {
        start = end;
        end = start + chunk + (rest-- > 0);
        threads.emplace_back(parallelConvolution, ref(input), ref(kernel), ref(output), start, end);
    }

    for (auto& thread : threads) {
        if (thread.joinable()) {
            thread.join();
        }
    }

    auto endTime = high_resolution_clock::now();
    double difference = duration<double, milli>(endTime - startTime).count();
    cout << difference << endl;
}

void checkCompliance(const Matrix& matrix1, const Matrix& matrix2) {
    if (matrix1.rows != matrix2.rows || matrix1.cols != matrix2.cols) {
        throw runtime_error("Matrices have different dimensions");
    }

    for (int i = 0; i < matrix1.rows; ++i) {
        for (int j = 0; j < matrix1.cols; ++j) {
            if (matrix1.data[i][j] != matrix2.data[i][j]) {
                throw runtime_error("Matrices are not equal");
            }
        }
    }
}

int main(int argc, char** argv) {
    int p = atoi(argv[1]);

    Matrix input = createEmptyMatrix(MAX_SIZE, MAX_SIZE);
    Matrix kernel = createEmptyMatrix(MAX_KERNEL_SIZE, MAX_KERNEL_SIZE);
    Matrix output = createEmptyMatrix(MAX_SIZE, MAX_SIZE);

    readMatrix("input.txt", input);
    readMatrix("kernel.txt", kernel);

    int offset = (kernel.rows - 1) / 2;

    if (p == 0) {
        sequentialConvolution(input, kernel, output);
    }
    else {
        parallelization(input, kernel, output, p);
    }

    writeMatrix("output.txt", output);

    if (p == 0) {
        writeMatrix("valid.txt", output);
    }
    else {
        Matrix validOutput = createEmptyMatrix(MAX_SIZE, MAX_SIZE);
        readMatrix("valid.txt", validOutput);
        checkCompliance(output, validOutput);
    }

    return 0;
}
