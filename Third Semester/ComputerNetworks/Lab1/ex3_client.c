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
    printf("Eroare la crearea socketului client\n");
    return 1;
  }
  
  memset(&server, 0, sizeof(server));
  server.sin_port = htons(1234);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  
  if (connect(c, (struct sockaddr *) &server, sizeof(server)) < 0) {
    printf("Eroare la conectarea la server\n");
    return 1;
  }
  
  char sendString[256];
  printf("Enter  the string: ");
  fgets(sendString, 256, stdin);

  uint16_t lengthOfString = strlen(sendString);
  lengthOfString = htons(lengthOfString);

  send(c, &lengthOfString, sizeof(uint16_t),0);
  send(c, sendString, sizeof(sendString), 0);

  char recievedString[256];
  recv(c, &recievedString, sizeof(recievedString), 0);


  printf("The reversed string  is : %s!\n",recievedString);

  close(c);
}
