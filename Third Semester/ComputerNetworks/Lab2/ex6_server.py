import socket

ip = "127.0.0.1"
port = 1234

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((ip,port))


while True:
    string, addr = sock.recvfrom(1024)
    char, addr = sock.recvfrom(1024)
    string = string.decode()
    char = char.decode()
    charApp = 0
    for charS in string:
        if charS == char :
            charApp += 1

    

    print(string)
    sock.sendto(str(charApp).encode(),addr)
