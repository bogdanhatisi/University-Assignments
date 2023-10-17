//Unul dintre programe contine doua erori care sunt in acelasi timp
//erori in limbajul original (pentru care MLP defineste un subset)

#include<iostream>
using namespace std;
int main(){
	int error;
	12=error; // cannot assign variable to integer
	error=4 // missing semi-colon
	return 0;
}