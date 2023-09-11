import pandas as pd
import numpy as np
import math
import scipy.stats as st
import matplotlib.pyplot as plt


p = 0.4 

pe1 = 1 - ((1-p) ** 5)
pe2 = (1-p)**2
pe3 = pe1 * pe2 
pe4 = pe1 + pe2 - pe3 

print(pe1)
print(pe2)
print(pe3)
print(pe4)

df = pd.read_csv('C:\\Users\\Simox\\Desktop\\Università\SAD\Esame\Esami completi\DATI-AMBIENTE.txt', sep=";", decimal=",", na_values=' ')
df.info()

print(len(df))

df["PROVINCIA"].value_counts(sort=False).plot.bar()
plt.show()

province = df["PROVINCIA"].value_counts()
print(min(province))
print(max(province))

province = df["PROVINCIA"].value_counts(normalize=True)









