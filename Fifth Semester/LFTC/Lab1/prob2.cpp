// determina cmmdc a 2 nr naturale

#include<iostream>
using namespace std;
int main(){
	int a,b;
	cin>>a>>b;
	while(a!=b) {
		if(a>=b) {
			a=a–b;
		}
		if(a<b) {
			b=b-a;
		}
	}
	cout<<a;
	return 0;
}