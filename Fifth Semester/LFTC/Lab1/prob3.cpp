//calculeaza suma a n numere citite de la tastatura

#include<iostream>
using namespace std;
int main(){
	int n, a, suma;
	s=0;
	cin>>n;
	int i;
	i=1;
	while(i<=n){
		cin>>a;
		suma=suma+a;
		i=i+1;
	}
	cout<<suma;
	return 0;
}
