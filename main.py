import socket
from math import *


def main():
    host = "localhost"
    port = 1234
    clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    clientSocket.connect((host, port))
    arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
    arrString = ','.join(map(str, arr)) + '\n'
    clientSocket.sendall(arrString.encode())
    response = clientSocket.recv(1024).decode()
    arr = list(map(int, response.split(",")))
    size = int(sqrt(len(arr)))
    for i in range(0, len(arr)):
        if i % size == 0:
            print()
        print(str(arr[i]), end=" ")
    clientSocket.close()


if __name__ == '__main__':
    main()
