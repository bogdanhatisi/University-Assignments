import socket

ip = "127.0.0.1"

port = 1234

sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)

number = int(input("Enter a number: "))
numberBinary = number.to_bytes(1,'big')

print(numberBinary)

sock.sendto(numberBinary,(ip,port))

divisors, addr = sock.recvfrom(1024)

divisors = int(divisors.decode())

print(f"Number of divisors: {divisors}")
