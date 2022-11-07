def isPrime(number):
    numberOfDivisors = 0
    divisor = 2
    while divisor < number/2:
        if number%divisor==0:
            numberOfDivisors += 1
        divisor += 1

    if numberOfDivisors > 0:
        return 0
    else:
        return 1


import socket

ip = "127.0.0.1"
port = 1234

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((ip,port))

while True:
    checkNumber, addr = sock.recvfrom(1024)
    checkNumber = int(checkNumber)
    prime = str(isPrime(checkNumber))
    sock.sendto(prime.encode(),addr)


