import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib as plot

heroes = pd.read_csv("heroes.csv", sep=";", index_col=0)

male_strength_freq = pd.crosstab(index=heroes.loc[heroes['Gender']=='M',
                                                  'Strength'],
                                 columns='Abs. freq.')
female_strength_freq = pd.crosstab(index=heroes.loc[heroes['Gender']=='F',
                                                    'Strength'],
                                   columns='Abs. freq.')

num_male = sum(male_strength_freq['Abs. freq.'])
num_female = sum(female_strength_freq['Abs. freq.'])

print('Ci sono {} supereroi e {} supereroine'.format(num_male, num_female))


male_strength_freq = (pd.crosstab(index=heroes.loc[heroes['Gender']=='M',
                                                   'Strength'],
                                 columns='Rel. freq.',
                                 normalize=True)
                        .loc[:, 'Rel. freq.'])
female_strength_freq = (pd.crosstab(index=heroes.loc[heroes['Gender']=='F',
                                                     'Strength'],
                                   columns='Rel. freq.',
                                   normalize=True)
                          .loc[:, 'Rel. freq.'])

male_strength_freq.plot(marker='o', color='blue', legend=False)
female_strength_freq.plot(marker='o', color='pink', legend=False)
plt.show()


gender_freq = pd.crosstab(index= heroes['Gender'],
                          columns=['Abs. frequence'],
                          colnames=[''])

heroes['Weight'].hist(bins=50)
plt.show()

#frequenza cumulata: somma delle frequenze fino a quel punto

#frequenze congiunte
int_gender_freq = pd.crosstab(index=heroes['Intelligence'], 
                              columns=heroes['Gender'])

int_gender_freq = int_gender_freq.reindex(['low', 'moderate',
                                           'average', 'good', 'high'])


int_gender_freq.plot.bar(color=['pink', 'blue'])
plt.show()


pd.crosstab(index=pd.cut(heroes['Weight'],
                         bins=[30, 50, 80, 100, 200, 500, 1000],
                         right=False),
            columns=[heroes['Gender']])




heroes[heroes['Gender']=='M'].plot.scatter('Height', 'Weight')
plt.show()










