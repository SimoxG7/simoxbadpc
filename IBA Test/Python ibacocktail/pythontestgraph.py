import numpy as np
import matplotlib.pyplot as plt
import csv #comma separated values

x = np.linspace(0, 20, 100)  # Create a list of evenly-spaced numbers over the range
plt.plot(x, np.sin(x))       # Plot the sine of each x point
plt.show()                   # Display the plot


x = ["a", "b", "c"]
y = [1, 67, 25]
plt.rc('figure', figsize=(5.0, 2.0))
plt.bar(x, y)
plt.show()
