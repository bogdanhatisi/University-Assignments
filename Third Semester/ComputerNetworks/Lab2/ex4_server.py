import socket

ip = "127.0.0.1"
port = 1234

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((ip,port))


while True:
    string, addr = sock.recvfrom(1024)
    string = string.decode()
    string = string[::-1]
    

    print(string)
    sock.sendto(string.encode(),addr)
