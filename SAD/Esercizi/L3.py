import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from scipy.constants import golden

plt.style.use('fivethirtyeight')
plt.rc('figure', figsize=(5.0, 5.0/golden))


heroes = pd.read_csv('heroes.csv', sep=';', index_col=0)
heroes_with_year = heroes[heroes['First appearance'] < 2020]

print(heroes_with_year.head(10))


#si parla di dati quantitativi se l'esito della misurazione è una quantità numerica; (height, weight, strength). Dati quantitativi possono essere discreti o continui in base ai valori che possono assumere. 

#si parla invece di dati qualitativi (o categorici, o nominali) quando la misurazione è fatta scegliendo un'etichetta a partire da un insieme disponibili. (hair color, eye color... ). Spesso classificati come booleani, nominali oppure ordinali. 

#frequenza assoluta: numero di volte che un dato compare in un campione.
print(heroes_with_year['Publisher'].value_counts()) # --> dieci valori possibili per l'editore. 

#crosstab di pandas fornisce una visualizzazione più elegante, tipo dataframe
publisher_freq = pd.crosstab(index=heroes_with_year['Publisher'], columns=['Abs. frequence'], colnames=[''])
#index di crosstab viene 

print(publisher_freq)

#per frequenze relative
publisher_abs_freq = pd.crosstab(index=heroes_with_year['Publisher'],
                                 columns=['Rel. frequence'],
                                 colnames=[''])

publisher_rel_freq = publisher_abs_freq / publisher_abs_freq.sum()

print(publisher_rel_freq)

#oppure:
publisher_rel_freq = pd.crosstab(index=heroes_with_year['Publisher'],
                                 columns=['Rel. frequence'],
                                 colnames=[''], normalize=True)

print(publisher_rel_freq)

publisher_rel_freq.apply(lambda n: np.round(n, 2))

print(publisher_rel_freq)

















