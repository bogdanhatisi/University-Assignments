import collections
import math
from queue import Queue


# Functii auxiliare

def test_dimensiune(testlist, dimensiune=0):
   """Testeaza dimensiunile listei date, daca lista este goala returneaza -1, iar altfel dimensiunea listei"""
   if isinstance(testlist, list):
      if testlist == []:
          return dimensiune
      dimensiune = dimensiune + 1
      dimensiune = test_dimensiune(testlist[0], dimensiune)
      return dimensiune
   else:
      if dimensiune == 0:
          return -1
      else:
          return dimensiune

def calculeaza_frecventa(list):
    """Calculeaza frecventa elementelor unei liste date, returneaza dictionarul cu frecventele elementelor"""
    frecventa = {}

    for item in list:
        if item in frecventa:
            frecventa[item] +=1
        else:
            frecventa[item] = 1

    return frecventa

def calculeaza_submatrice(matrice,a,b,c,d):
    """Calculeaza suma elementelor submatricii determinate de punctele (a,b) si (c,d), iar mai apoi o returneaza"""
    i = a
    j = b
    suma = 0
    while i <= c:
        j = b
        while j <= d:
            suma += matrice[i][j]
            j += 1
        i += 1

    return suma

# Functiile ce rezolva fiecare problema


def problema_1(text):
    print("\nProblema 1 raspuns:")
    if(len(text) == 0):
        print("Text invalid!")
        return 0

    list = text.split()

    ultim_cuv = "a"

    for item in list:
        if item > ultim_cuv:
            ultim_cuv = item

    print(ultim_cuv)


def problema_2(x1,y1,x2,y2):
    print("\nProblema 2 raspuns:")
    # Distanta euclidiana: radical din (x2-x1)^2 + (y2-y1)^2
    x = x2 - x1
    y = y2 - y1
    distanta = math.sqrt(pow(x, 2) + pow(y, 2))
    print(distanta)

def problema_3(vector1,vector2):
    print("\n Problema 3 raspuns:")
    produs = 0
    i = 0
    if test_dimensiune(vector1) == 0 or test_dimensiune(vector2) == 0:
        print("Vectorii trebuie sa fie valizi")
        return 0
    elif test_dimensiune(vector1) == 1:
        while i < len(vector1):
            produs += vector1[i]*vector2[i]
            i += 1
    elif test_dimensiune(vector1) == 2:
        while i < len(vector1[0]):
            produs_col = 0
            j = 0
            while j < len(vector1):
                produs_col += vector1[j][i] * vector2[j][i]
                j += 1
            produs += produs_col
            i += 1


    print(produs)

def problema_4(text):
    print("\nProblema 4 raspuns:")
    list = text.split()
    frecventa = calculeaza_frecventa(list)
    for key,value in frecventa.items():
        if value == 1:
            print(key)


def problema_5(vector):
    print("\nProblema 5 raspuns:")
    frecventa = calculeaza_frecventa(vector)
    i = 0
    for key, value in frecventa.items():
        if value == 2:
            print(key)

def problema_6(vector):
    print("\nProblema 6 raspuns:")
    frecventa = calculeaza_frecventa(vector)
    i = 0
    for key, value in frecventa.items():
        if value > len(vector)/2:
            print(key)


def problema_7(vector,k):
    print("\nProblema 7 raspuns:")
    vector.sort(reverse=1)
    print(vector[k-1])


def problema_8(n):
    print("\nProblema 8 raspuns:")
    coada = Queue()
    numar_curent = 1
    coada.put("1")

    while n>0:
        n -= 1
        x1 = coada.get()
        print(x1)

        x2 = x1
        coada.put(x1+"0")
        coada.put(x2+"1")


def problema_9(matrice,lista_perechi):
    print("\nProblema 9 raspuns:")
    calculate_deja = {}
    if len(lista_perechi) == 0:
        print("Va rugam sa introduceti perechi!")
        return 0

    for pereche in lista_perechi:
        suma = 0
        print(pereche)
        a = pereche[0][0]
        b = pereche[0][1]
        c = pereche [1][0]
        d = pereche [1][1]
        suma = calculeaza_submatrice(matrice,a,b,c,d)
        print(suma)


def problema_10(matrice):
    print("\nProblema 10 raspuns:")

    if len(matrice) == 0:
        print("Va rugam sa introduceti o matrice ce contine cel putin 1 element!")
        return 0

    frecventa = {}

    i = 0
    max = 0
    max_indice = 0

    while i < len(matrice):
        frecventa_linie = calculeaza_frecventa(matrice[i])
        frecventa[i] = frecventa_linie[1]
        if(frecventa[i] > max):
            max = frecventa[i]
            max_indice = i
        i += 1

    print(f"Linia cu cele mai multe elemente de 1 este: {max_indice}")

# Rulam 3 exemple pe fiecare problema

if __name__ == '__main__':

    print("\n ===| CAZURI PROBLEMA 1 |================")
    # Cazuri problema 1
    problema_1("Ana are mere rosii si galbene")
    problema_1("Ana are mere rosii si galbene, zece din fiecare")
    problema_1("")

    # Cazuri problema 2
    print("\n ===| CAZURI PROBLEMA 2 |================")
    problema_2(1,5,4,1)
    problema_2(0,0,0,0)
    problema_2(-1,-2,-3,-4)

    # Cazuri problema 3
    print("\n ===| CAZURI PROBLEMA 3 |================")
    matrice1 = [[1,0,2,0,3],[0,0,0,0,0]]
    problema_3([1,0,2,0,3],[1,2,0,3,1])
    problema_3([1,1,1,1],[2,2,2,2])
    problema_3(matrice1,matrice1)

    # Cazuri problema 4
    print("\n ===| CAZURI PROBLEMA 4 |================")
    problema_4("ana are ana are mere rosii ana")
    problema_4("test1 test test ana ana ana mere mere mere mouse iese iese")
    problema_4("test test test test test")

    # Cazuri problema 5
    print("\n ===| CAZURI PROBLEMA 5 |================")
    problema_5([1,2,3,4,2])
    problema_5([1,2,3,4,3,5,6,7,8,889])
    problema_5([1, 2, 3, 4, 3, 5, 6, 7, 8, 889, 2, 3, 4, 3, 5, 6, 7, 8, 889,2, 3, 4, 3, 5, 6, 7, 8, 889,62,63,62])

    # Cazuri problema 6
    print("\n ===| CAZURI PROBLEMA 6 |================")
    problema_6([2,8,7,2,2,5,2,3,1,2,2])
    problema_6([2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1])
    problema_6([3,3,3,2,2,2])

    # Cazuri problema 7
    print("\n ===| CAZURI PROBLEMA 7 |================")
    problema_7([7,4,6,3,9,1],2)
    problema_7([101,102,103,104,105,106,107,108,110,112,114,116,118,2312,2500],3)
    problema_7([-1,-2,-3,-4,-5],2)

    # Cazuri problema 8
    print("\n ===| CAZURI PROBLEMA 8 |================")
    problema_8(4)
    problema_8(15)
    problema_8(100)

    # Cazuri problema 9
    print("\n ===| CAZURI PROBLEMA 9 |================")
    problema_9([[0, 2, 5, 4, 1],
[4, 8, 2, 3, 7],
[6, 3, 4, 6, 2],
[7, 3, 1, 8, 3],
[1, 5, 7, 9, 4]],[[[1,1],[3,3]],[[2,2],[4,4]]])

    problema_9([[0, 2, 5, 4, 1],
                [4, 8, 2, 3, 7],
                [6, 3, 4, 6, 2],
                [7, 3, 1, 8, 3],
                [1, 5, 7, 9, 4],
                [12, 10, 1, 4, 5]], [[[0, 0], [0, 0]], [[2, 2], [5, 4]]])

    problema_9([[1,1,1],
                [2,2,2],
                [3,3,3]],[])

    # Cazuri problema 10
    print("\n ===| CAZURI PROBLEMA 10 |================")
    problema_10([[0,0,0,1,1],
[0,1,1,1,1],
[1,1,1,1,1]])
    problema_10([[0, 0, 0, 1, 1],
                 [0, 1, 1, 1, 1],
                 [0, 1, 1, 1, 1],
                 [0, 0, 0, 1, 1],
                 [0, 1, 1, 1, 1],
                 [0, 1, 1, 1, 1],
                 [0, 0, 0, 1, 1],
                 [0, 1, 1, 1, 1],
                 [1, 1, 1, 1, 1]
                 ])
    problema_10([])







