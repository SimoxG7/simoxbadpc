from locale import normalize
import numpy as np
import matplotlib.pyplot as plt
from scipy.constants import golden

plt.style.use('fivethirtyeight')
plt.rc('figure', figsize = (5.0, 5.0/golden))

#cattura il cocnetto di eterogeneità traducendolo in una quantità numerica 
def gini_2_val(f):
    return 1 - f**2 - (1-f)**2

x = np.arange(0, 1.01, 0.01)
y = list(map(gini_2_val, x))

plt.plot(x, y)
plt.ylim((0, 0.55))
plt.show()


def gini(series):
    return 1 - (sum(series.value_counts(normalize = True).map(lambda f: f**2)))

import pandas as pd

heroes = pd.read_csv("heroes.csv", sep = ";", index_col = 0) 

publisher = heroes[heroes['Publisher'].isin(["Marvel Comics", "DC Comics"])]["Publisher"]

eye_color = heroes[pd.notnull(heroes["Eye color"])]["Eye color"]

hair_color = heroes[pd.notnull(heroes["Hair color"])]["Hair color"]

print(gini(publisher))
print(gini(eye_color))
print(gini(hair_color))
print(gini(heroes.index))


def generalized_gini(series):
    freq = series.value_counts(normalize = True)
    s = len(freq)
    return (1 - (sum(freq.map(lambda f: f**2)))) * s / (s - 1)

print(generalized_gini(publisher))
print(generalized_gini(eye_color))
print(generalized_gini(hair_color))
print(generalized_gini(heroes.index))



# ENTROPIA: sommatoria delle frequenze per il log di 1/fi -> -sum (fi logfi) -> indice di entropia del campione

x = np.arange(0.01, 1.01, 0.01) #start, stop, step
y = list(map(lambda f: -1 * np.log2(f), x))
plt.plot(x, y)
plt.show()


x = np.arange(0.001, 1.001, 0.01)
y = list(map(lambda f: -f * np.log2(f), x))
plt.plot(x, y)
plt.ylim(0, 0.6)
plt.xlim(0, 1)
plt.show()

def entropy_2_val(f):
    return 0 if f in (0, 1) else -f * np.log2(f) - (1 - f) * np.log2(1 - f)

x = np.arange(0, 1.01, .01)
y = list(map(entropy_2_val, x))
plt.plot(x, y)
plt.ylim((0, 1.1))
plt.show()


def entropy(series):
    return sum((series.value_counts(normalize=True).map(lambda f: -f * np.log2(f))))

print(entropy(publisher))
print(entropy(eye_color))
print(entropy(hair_color))
print(entropy(heroes.index))  

def entropy(series, normalized=False):
    freq = series.value_counts(normalize=True)
    e = sum((freq.map(lambda f: -f * np.log2(f))))
    if normalized:
        e /= np.log2(len(freq))
    return e

print(entropy(publisher, normalized=True))
print(entropy(eye_color, normalized=True))
print(entropy(hair_color, normalized=True))
print(entropy(heroes.index, normalized=True))



# ALBERI DI DECISIONE 

#hanno alla base gli indici di eterogeneità 



















































