// ConsoleApplication1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>
#include <thread>
#include <vector>


using namespace std;
using namespace std::chrono;

int N, M, n, m, p;
int static_matrix[10][10];
int conv_matrix[3][3];

void read(string path)
{
    ifstream data(path);
    data >> N >> M;
    cout << N<<" "<< M;
    cout << endl;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)

        {
            data >> static_matrix[i][j];
            cout << static_matrix[i][j] << " ";
        }
        cout << endl;

    }
    
    data >> n >> m;
    cout << n << " " << m;
    cout << endl;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            data >> conv_matrix[i][j];
            cout << conv_matrix[i][j] << " ";
        }
        cout << endl;
    }

}

void applySingleConvultion(int a, int b, int border) {
    int result = 0;
    
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            int ii =
        }
    }
}



int main()
{
    string path = "data.txt";
    read(path);

    return 0;
}

