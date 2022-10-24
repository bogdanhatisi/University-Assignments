#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>


void mergeTwoSortedArrays(char firstArray[], int firstArrayLength, char secondArray[], int secondArrayLength, char mergedArray[])
{
    int i = 0, j = 0, k = 0;

    while (i < firstArrayLength && j < secondArrayLength) {
        if (firstArray[i] == '\n') {
            i++;
            continue;
        }

        if (secondArray[j] == '\n') {
            j++;
            continue;
        }

        if (firstArray[i] < secondArray[j])
            mergedArray[k++] = firstArray[i++];

        else
            mergedArray[k++] = secondArray[j++];
    }

    while (i < firstArrayLength) {
        if (firstArray[i] == '\n') {
            i++;
            continue;
        }

        mergedArray[k++] = firstArray[i++];
    }

    while (j < secondArrayLength) {
        if (secondArray[j] == '\n') {
            j++;
            continue;
        }

        mergedArray[k++] = secondArray[j++];
    }

}

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
    uint16_t stringLength,stringLengthTwo;

    int res = recv(c, (char*)&stringLength, sizeof(uint16_t), 0);
   
    stringLength = ntohs(stringLength);

    char recievedString[256];
    char recievedStringTwo[256];
    char resultString[512];

    recv(c, (char*)&recievedString, sizeof(recievedString), 0);

    recv(c, (char*)&stringLengthTwo, sizeof(uint16_t),0);

    stringLengthTwo = ntohs(stringLengthTwo);

    recv(c, (char*)&recievedStringTwo, sizeof(recievedStringTwo),0);
    
    uint16_t index = 0;

    mergeTwoSortedArrays(recievedString, stringLength, recievedStringTwo, stringLengthTwo, resultString);


    send(c, (char*)resultString, sizeof(resultString), 0);
    close(c);
    // sfarsitul deservirii clientului;
  }
}
