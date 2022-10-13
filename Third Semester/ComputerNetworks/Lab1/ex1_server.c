#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>

int main() {
  int s;
  struct sockaddr_in server, client;
  int c, l;
  
  s = socket(AF_INET, SOCK_STREAM, 0);
  if (s < 0) {
    printf("Could not create socket for the server\n");
    return 1;
  }
  
  memset(&server, 0, sizeof(server));
  server.sin_port = htons(1234);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  
  if (bind(s, (struct sockaddr *) &server, sizeof(server)) < 0) {
    printf("Bind error\n");
    return 1;
  }
 
  listen(s, 5);
  
  l = sizeof(client);
  memset(&client, 0, sizeof(client));
  
  while (1) {
    uint16_t a, b, suma;
    c = accept(s, (struct sockaddr *) &client, &l);
    printf("A new connection has been established.\n");
    // deservirea clientului
    recv(c, &a, sizeof(a), MSG_WAITALL);
    a = ntohs(a);
    suma = 0;
    
    for(int i=1; i<=a; i++)
    {
	
    recv(c,&b,sizeof(b), MSG_WAITALL);
    b = ntohs(b);
    suma = suma + b;
    }
    suma = htons(suma);
    send(c, &suma, sizeof(suma), 0);
    close(c);
    // sfarsitul deservirii clientului;
  }
}
