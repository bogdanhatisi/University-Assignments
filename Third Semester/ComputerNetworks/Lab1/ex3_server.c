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
    printf("Eroare la crearea socketului server\n");
    return 1;
  }
  
  memset(&server, 0, sizeof(server));
  server.sin_port = htons(1234);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  
  if (bind(s, (struct sockaddr *) &server, sizeof(server)) < 0) {
    printf("Eroare la bind\n");
    return 1;
  }
 
  listen(s, 5);
  
  l = sizeof(client);
  memset(&client, 0, sizeof(client));
  
  while (1) {
    c = accept(s, (struct sockaddr *) &client, &l);
    printf("S-a conectat un client.\n");
    // deservirea clientului
    uint16_t stringLength;

    int res = recv(c, (char*)&stringLength, sizeof(uint16_t), 0);
   
    stringLength = ntohs(stringLength);

    char recievedString[256];
    char reversedString[256];

    res = recv(c, (char*)&recievedString, sizeof(recievedString), 0);
	uint16_t numberOfSpaces = 0;
	for(uint16_t index = 0; index < stringLength; index++)
		reversedString[index] = recievedString[stringLength-index-1];


    send(c, (char*)reversedString, sizeof(reversedString), 0);
    close(c);
    // sfarsitul deservirii clientului;
  }
}
