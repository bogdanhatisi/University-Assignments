import socket

ip = "127.0.0.1"
port = 1234

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

string = str(input("Enter a string: "))

sock.sendto(string.encode(),(ip,port))

emptyChar, addr = sock.recvfrom(1024)

print(int(emptyChar))
