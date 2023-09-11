import csv
from operator import index
from tkinter import X
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from pandas.core.indexes.base import Index

print(pd.__version__)

plt.style.use("fivethirtyeight")
plt.rc("figure", figsize = (5.0, 2.0))

with open("heroes.csv", "r") as heroes_file:
    heroes_reader = csv.reader(heroes_file, delimiter=";", quotechar="\"")
    heroes = list(heroes_reader) [1:]

#print(heroes)

#series from pandas

years = [int(h[7]) if h[7] else None for h in heroes]
names = [h[0] for h in heroes]
first_appearance = pd.Series(years, index = names)

print(first_appearance) #rende None NaN

#accesso ai dati:

print(first_appearance.iloc[0]) #indirizzabile con integer di posizione o array di booleani
print(first_appearance.loc["Wonder Woman"]) #indirizzabile con nome indice

#slicing di serie comprendono anche l'ultimo indicato se tramite indice e non posizione (non è escluso l'estremo destro)
print(first_appearance["Wonder Girl":"Wonder Woman"])

#primi e ultimi n elementi di serie con head e tail:
print(first_appearance.head(9))
print(first_appearance.tail(12))


#accesso tramite lista di posizioni:
print(first_appearance[[0, 7, 69, 619]])

print(first_appearance.index.get_loc("Box")) #weird trick to get the index of something by the index

print(first_appearance[first_appearance > 2000])

#frequenze assolute:
print(first_appearance.value_counts())

#frequenze relative:
print(first_appearance.value_counts(normalize=True))

abs_freq = first_appearance.value_counts(sort=years)
abs_freq.plot.bar()
#plt.show()

"""
years = np.arange(1945, 2010, 10) #valori su ascisse da 10 in 10
index_pos = [first_app_freq.index.get_loc(y) for y in years] 
first_app_freq.plot.bar()
plt.xticks(index_pos, years)
plt.ylim((0, 18.5))
plt.show()
"""

print(sum(abs_freq))


height = pd.Series([float(h[4]) if h[4] else None for h in heroes])

print(height)

print(height.apply(lambda h: h/100))



#dataframe: collezione di serie con lo stesso indice. 

heroes = pd.read_csv("heroes.csv", sep = ";", index_col = 0)

#per ottenere insiemi di indici: 
print(heroes.index)

#per ottenere insiemi di colonne:
print(heroes.columns)

#per ottenere insiemi di valori:
heroes.values

heroes.loc["Box"]
print(heroes.iloc[heroes.index.get_loc("Box")])

print(heroes.at["Superman", "Strength"])
























