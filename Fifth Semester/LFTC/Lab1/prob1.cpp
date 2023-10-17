//calculeaza perimetrul si aria cercului de o raza data data

#include<iostream>
using namespace std;
int main(){
	int raza;
	float pi;
	pi = 3.14;
	cin>>raza;
	cout<<”arie: “<< pi*raza*raza;
	cout<<”permietru: “<< 2*pi*raza;
	return 0;
}
