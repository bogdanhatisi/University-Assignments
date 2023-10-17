//Al doilea program contine doua erori conform MLP, dar care nu sunt
//erori in limbajul original. Se cere ca acesta sa fie compilat si
//executat in limbajul original ales.


#include<iostream>
using namespace std;
int main(){
	int error=34; // cannot assign value in variable declaration
	error=error-1;
	cout<<error;
	// missing return statement
}