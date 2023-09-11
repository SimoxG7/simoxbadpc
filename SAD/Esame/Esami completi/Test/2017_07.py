import numpy as np
import scipy.stats as st
import matplotlib.pyplot as plt

X = st.norm(200, 50)
x = np.linspace(0, 400, 50) #start, stop, numero di sample da generare
plt.plot(x, X.pdf(x))
plt.show()
