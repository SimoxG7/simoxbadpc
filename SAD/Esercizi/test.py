from funmath import *

anno = 1992
print(type(anno))

#lista
iron_man = ['Iron Man',
            'Tony Stark',
            'Long Island, New York',
            'Marvel Comics',
            198.51,
            191.85,
            'M',
            1963,
            'Blue',
            'Black',
            85,
           'high']

print(type(iron_man))

print(iron_man)

print(iron_man[3:6])

print(iron_man[0], "-", iron_man[-1])

print('White' in iron_man)
print('Black' in iron_man)
print('black' in iron_man)

print(type(len(iron_man)) , len(iron_man))

drinks = ["Al3", "Ai33", "Zmeme", "rts", "AAA"]

sorted_drinks = sorted(drinks)

print("drinks:", drinks)
print("sorteddrinks:", sorted_drinks)
print("drinks:", drinks)
drinks.sort()
print("drinks:", drinks)

func = lambda n: len(n)

sortoption = lambda n:len(n)

drinks.sort(key = len, reverse = False)
print(drinks)


#tupla
tupla = (15, 23, 444)

try:
    tupla.sort()
except :
    print("An exception occurred")

print(tupla[0])


#stringhe -> tuple di caratteri (unmodifiable)

name = "hello"
print(name[2])


#insiemi
set = {"a", "b", "a"}
print(set)


#dizionari (mappe)

dict = {"a":32, "b":47}
print(dict)

print(dict["a"])



for elem in drinks:
    print(elem)


counts = {}
for name in drinks:
    for letter in name:
        if name in counts:
            counts[name] += 1
        else:
            counts[name] = 1

print(counts)


pairs = counts.items()

print(sorted(pairs, key = lambda list: len(list[0]), reverse = True))

def sort_by_length(list):
    newlist = sorted(list, key = lambda n: len(n))
    return newlist

print(sort_by_length(counts))

print(fibo(15))

print(fact(5))

#https://dariomalchiodi.gitlab.io/sad-python-book/L01-Introduzione_a_python.html#h-0


