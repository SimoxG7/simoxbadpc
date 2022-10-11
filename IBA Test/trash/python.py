# py per aprire interprete

# to comment

# + - * / % // divisione intera ** to the power

#strings in " " or ' ', \ per escape sequences


"doesn\'t"
"helloOOOOOO"
"\"\""

from stat import FILE_ATTRIBUTE_COMPRESSED


print()

s = "hello"
print(s)

s = "\\path\\"
print(s)

# \n, \t

s = r"line" #rawline, non considera sequenze di escape
print(s)

# """...""" per string literals

# s + t dà concatenazione tra stringhe

n = 0

s[n] #dà il carattere in posizione ennesima della stringa, se n negativo conta da destra (-1 è last char)

m = 3
s[n:m] #slice di s da n incluso a m escluso. se omesso n, è 0, se omesso m, è size della stringa

# stringhe di python sono immutabili, non posso fare assegnamento a char di stringa

len(s) # lunghezza s

lista = [1, 2, 3, 4, 10, 24, "hello"] #lista è tra quadre e con elementi separati da virgole

#fare slice di lista ti ritorna una nuova lista. fare lista[0] ritorna il primo elemento. lista[-1] ritorna l'ultimo elemento.

#liste supportano concatenazione con + 

#liste sono ovviamente mutabili.

lista.append("x")

# si possono fare assegnamenti a slice anche

lista[2:4] = []  # fa diventare secondo e quarto elemento vuoti

len(lista)

#le liste sono annidabili

a, b = 0, 1


while 1:
    print("kys")

nome = "Simox"
print("Il mio nome è", nome)

#keyword end per non fare il newline
print("Il mio nome è", end=nome)

#fibonacci
a, b = 0, 1
while a < 10000:
    print(a, end=', ')
    a, b = b, a+b

x = 3

#if 
if x < 0:
    x = 0
    print('Negative changed to zero')
elif x == 0:
    print('Zero')
elif x == 1:
    print('Single')
else:
    print('More')


#il for di python itera su elementi di una sequenza (lista o stringa)

words = ["tea", "culona"]

for w in words:
    print(w, len(w))


# Create a sample collection
users = {'Hans': 'active', 'Éléonore': 'inactive', '景太郎': 'active'}

# Strategy:  Iterate over a copy
for user, status in users.copy().items():
    if status == 'inactive':
        del users[user]

# Strategy:  Create a new collection
active_users = {}
for user, status in users.items():
    if status == 'active':
        active_users[user] = status


for i in range(5): #stampa da 0 a 4 incluso
    print(i)

for w in range(len(words)):
    print(w, words[w])

#usare enumerate()

sum(range(4)) # 0 + 1 + 2 + 3

#break # esce dal loop

#continue skippa questo ciclo

#pass non fa nulla (placeholder o se serve uno statement)

def http_error(status):
    match status:
        case 30 | 31:
            return "wtf"
        case 2:
            return "kys"
        case _: #default
            return "dumbass"


class Point:
    x: int
    y: int

def where_is(point):
    match point:
        case Point(x=0, y=0):
            print("Origin")
        case Point(x=0, y=y):
            print(f"Y={y}")
        case Point(x=x, y=0):
            print(f"X={x}")
        case Point():
            print("Somewhere else")
        case _:
            print("Not a point")


#per usare costanti con nome
from enum import Enum
class Color(Enum):
    RED = 'red'
    GREEN = 'green'
    BLUE = 'blue'

color = Color(input("Enter your choice of 'red', 'blue' or 'green': "))

match color:
    case Color.RED:
        print("I see red!")
    case Color.GREEN:
        print("Grass is green")
    case Color.BLUE:
        print("I'm feeling the blues :(")



#funzioni
def fib(n): #writes the fibonacci series up to n
    """Prints the Fibonacci series up to n.""" # string literal that is the function's documentation string
    a, b = 0, 1
    while a < n:
        a, b = b, a+b
        print(a, end=' ')
    print()

fib(2000)


# all functions return None, a built-in name.


def fibAsList(n): #writes the fibonacci series up to n
    """Returns a list containing the Fibonacci series up to n.""" # string literal that is the function's 
    a, b = 1, 1
    values = []
    while a < n:
        values.append(a)
        a, b = b, a+b
    return values

#booleani con True e False


#per vedere se è in sequenza
n = "ye"
if n in words:
    print("Let'sgooo")


#interessante (default value)

i = 5

def f(arg=i):
    print(arg)

i = 6
f()


################
def f(a, L=[]):
    L.append(a)
    return L

print(f(1))
print(f(2))
print(f(3))

#This will print

#[1]
#[1, 2]
#[1, 2, 3]

def f(a, L=None):
    if L is None:
        L = []
    L.append(a)
    return L

#questo stampa corretto


#kwarg=value keyword arguments

"""
def f(pos1, pos2, /, pos_or_kwd, *, kwd1, kwd2):
      -----------    ----------     ----------
        |             |                  |
        |        Positional or keyword   |
        |                                - Keyword only
         -- Positional only
"""


# https://docs.python.org/3.10/tutorial/datastructures.html

#The list data type has some more methods. Here are all of the methods of list objects:

list.append(x)

    #Add an item to the end of the list. Equivalent to a[len(a):] = [x].

list.extend(iterable)

    #Extend the list by appending all the items from the iterable. Equivalent to a[len(a):] = iterable.

list.insert(i, x)

    #Insert an item at a given position. The first argument is the index of the element before which to insert, so a.insert(0, x) inserts at the front of the list, and a.insert(len(a), x) is equivalent to a.append(x).

list.remove(x)

    #Remove the first item from the list whose value is equal to x. It raises a ValueError if there is no such item.

list.pop([i])

    #Remove the item at the given position in the list, and return it. If no index is specified, a.pop() removes and returns the last item in the list. (The square brackets around the i in the method signature denote that the parameter is optional, not that you should type square brackets at that position. You will see this notation frequently in the Python Library Reference.)

list.clear()

    #Remove all items from the list. Equivalent to del a[:].

list.index(x[, start[, end]])

    #Return zero-based index in the list of the first item whose value is equal to x. Raises a ValueError if there is no such item.

    #The optional arguments start and end are interpreted as in the slice notation and are used to limit the search to a particular subsequence of the list. The returned index is computed relative to the beginning of the full sequence rather than the start argument.

list.count(x)

    #Return the number of times x appears in the list.

list.sort(*, key=None, reverse=False)

    #Sort the items of the list in place (the arguments can be used for sort customization, see sorted() for their explanation).

list.reverse()

    #Reverse the elements of the list in place.

list.copy()

    #Return a shallow copy of the list. Equivalent to a[:].

del words[0] #elimina da lista




# insiemi (no duplicati)
set() 

parole = {1, 2, "azzo"} 



# dizionari (mappe) (key:value)

tel = {'jack': 4098, 'sape': 4139}

sorted(tel)

"jack" in tel #true

#looping through dictionary's elements
knights = {'gallahad': 'the pure', 'robin': 'the brave'}
for k, v in knights.items():
   print(k, v)




#To loop over two or more sequences at the same time, the entries can be paired with the zip() function.


questions = ['name', 'quest', 'favorite color']
answers = ['lancelot', 'the holy grail', 'blue']
for q, a in zip(questions, answers):
    print('What is your {0}?  It is {1}.'.format(q, a))




# To loop over a sequence in reverse, first specify the sequence in a forward direction and then call the reversed() function.



import nomefile

from fibo import funct1




#per stampare piu bello
year = 2016
event = 'Referendum'
f'Results of the {year} {event}'


#https://docs.python.org/3.10/tutorial/inputoutput.html












