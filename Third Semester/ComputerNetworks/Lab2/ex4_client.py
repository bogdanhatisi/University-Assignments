import socket

ip = "127.0.0.1"
port = 1234

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

string = str(input("Enter a string: "))

sock.sendto(string.encode(),(ip,port))

reversedString, addr = sock.recvfrom(1024)

print(reversedString.decode())
