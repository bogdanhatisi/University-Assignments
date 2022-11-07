import socket

ip = "127.0.0.1"
port = 1234

number = str(input("Enter a number: "))

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.sendto(number.encode(),(ip,port))

response, addr = sock.recvfrom(1024)

response = int(response)
if response:
    print("The number is a prime one")
else:
    print("The number is not a prime one")
