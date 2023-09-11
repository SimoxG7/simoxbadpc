from math import sqrt
from operator import index
import pandas as pd
import numpy as np
import scipy.stats as st
import matplotlib.pyplot as plt

file = pd.read_csv(r"C:\\Users\Simox\Desktop\\Università\SAD\\Esame\\Esami completi\\Comune_Bergamo_-_Incidenti_stradali.csv")

print(len(file))
file.describe()

incidenti1998 = file[(file["Anno"] == 2002) | (file["Anno"] == 2003)]

print(incidenti1998)



"""
print(file.columns)
print(len(file))

print(file)

print(file.info())

anno = file.get("Anno")

print(anno)

#file['Anno'].value_counts(sort=False).plot.bar()
#plt.show()

nferiti = file["N_Feriti"].value_counts(normalize=True)

print(nferiti)

#nferiti.cumsum().plot() 
#plt.show()

print(1 - nferiti[0])

print(file["N_Feriti"].mean())

print(file['N_Feriti'].describe())

expect = file["N_Feriti"].mean() 

p = 1 / (1 + expect)
print(p)


#X = st.geom(p,loc=-1)
#x = np.arange(11) #numero max di incidenti
#plt.plot(x,X.pmf(x),'o')
#plt.show()


plt.scatter(file['N_Feriti'],file['N_Illesi'],color="brown")
plt.xlabel('Feriti')
plt.ylabel('Illesi')
#plt.show()

print(file['N_Feriti'].corr(file['N_Illesi']))

print(file["N_Illesi"].mean())

std = sqrt(file["N_Illesi"].var() / len(file))

print(std)

Z = st.norm()
print(Z.ppf(0.995))
"""


