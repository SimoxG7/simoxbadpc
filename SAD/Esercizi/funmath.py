def fibo(n):
    if n == 1 or n == 0:
        return 1
    else: 
        return fibo(n-1) + fibo(n-2)

def fact(n):
    if n == 1:
        return n
    else:
        return n*fact(n-1)
    