import socket


ip = "127.0.0.1"
port = 1234
firstNumber = str(input("Enter the first number: "))
secondNumber = str(input("Enter the second number: "))


sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.sendto(firstNumber.encode(), (ip,port))
sock.sendto(secondNumber.encode(), (ip,port))
