def numberOfDivisors(n):
    divisors = 1
    d = 1

    while d <= n/2:
        if n%d==0:
            divisors +=1 
        d += 1

    return divisors



import socket

ip = "127.0.0.1"

port = 1234

sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
sock.bind((ip,port))

while True:
    number, addr = sock.recvfrom(1024)
    intNumber = int.from_bytes(number, "big")
    divisors = numberOfDivisors(intNumber)
    sock.sendto(str(divisors).encode(),addr)

