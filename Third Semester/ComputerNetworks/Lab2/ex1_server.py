import socket

ip = "127.0.0.1"

port = 1234

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((ip,port))
sumOf = 0
while True:
    first, addr = sock.recvfrom(1024)
    second, addr = sock.recvfrom(1024)
    firstNumber = int(first)
    secondNumber = int(second)


    print(f"Recieved the follwing numbers: {firstNumber} and {secondNumber}")
    sumOf = firstNumber + secondNumber
    print(f"The sum is: {sumOf}")
