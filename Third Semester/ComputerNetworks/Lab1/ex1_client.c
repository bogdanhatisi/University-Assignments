#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

int main() {
  int c;
  struct sockaddr_in server;
  uint16_t a, b, suma;
  
  c = socket(AF_INET, SOCK_STREAM, 0);
  if (c < 0) {
    printf("Creating socket error\n");
    return 1;
  }
  
  memset(&server, 0, sizeof(server));
  server.sin_port = htons(1234);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  
  if (connect(c, (struct sockaddr *) &server, sizeof(server)) < 0) {
    printf("Connection error\n");
    return 1;
  }
  
  printf("a = ");
  scanf("%hu", &a);
  int x = a;
  a = htons(a);
  send(c, &a, sizeof(a),0);
  
  for(int i = 1; i <= x; i++)
  {
  printf("b = ");
  scanf("%hu",&b);
  b = htons(b);
  send(c, &b, sizeof(b), 0);
  }

  recv(c, &suma, sizeof(suma), 0);
  suma = ntohs(suma);
  printf("Sum is %hu\n", suma);
  close(c);
}
