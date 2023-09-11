from posixpath import split
import pandas as pd
import matplotlib.pyplot as plt
import scipy.stats as st
import math


df = pd.read_csv("C:\\Users\\Simox\\Desktop\\Università\\SAD\\Esame\\Esami completi\\dati-ospedali_new.csv", sep=";")

print(len(df)) # numero di ospedali nel dataset 

print(len(df.columns)) # numero di colonne

medici = df["MediciSSN"]

medici.hist(bins=20)
plt.show()

from statsmodels.distributions.empirical_distribution import ECDF
dist = ECDF(medici)
plt.plot(dist.x, dist.y)
plt.show()



