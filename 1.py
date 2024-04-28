import glob
import json
from pathlib import Path

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.cluster import KMeans

sessions = []
a = []

unique_ids = []
for year in range(2023, 2025):
    for month in range(1, 13):
        for day in range(1, 32):
            ids = glob.glob(
                'C:/Users/ignat/PycharmProjects/eco/csv/' + str(year) + '/' + str(month) + '/' + str(day) + '/*.csv')
            if len(ids) == 0:
                continue
            for i in range(len(ids)):
                id = ids[i]
                print(id)
                reader = pd.read_csv(id, sep=";")
                h = reader.head(1)
                lat, lon = h['lat'][0], h['lon'][0]
                id = Path(id).stem
                unique_ids.append([id, lat, lon])
                # unique_ids.append(id)
                # a.append()
unique_ids = pd.DataFrame(unique_ids, columns=['id', 'lat', 'lon'])
unique_ids = unique_ids.drop_duplicates(subset=['id'], keep='first')
id_columns = unique_ids['id'].reset_index(drop=True)
X = unique_ids[['lon', 'lat']].values

km = KMeans(n_clusters=10, random_state=0)
km.fit(X)

# Create a meshgrid of points covering the range of data points
x_min, x_max = X[:, 0].min() - 0.01, X[:, 0].max() + 0.01
y_min, y_max = X[:, 1].min() - 0.01, X[:, 1].max() + 0.01
plt.xlim(x_min, x_max)
plt.ylim(y_min, y_max)
plt.xticks(np.arange(x_min, x_max, 0.05))  # Задаем метки на оси X с шагом 0.005
plt.yticks(np.arange(y_min, y_max, 0.01))
plt.xlabel('xlabel', fontsize=8)
plt.ylabel('ylabel', fontsize=2)
xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.01),
                     np.arange(y_min, y_max, 0.01))

# Predict cluster labels for each point on the meshgrid
Z = km.predict(np.c_[xx.ravel(), yy.ravel()])
Z = Z.reshape(xx.shape)

# Plot the decision boundaries
plt.contourf(xx, yy, Z, alpha=0.4)
plt.scatter(X[:, 0], X[:, 1], c=km.labels_, cmap='viridis')
plt.scatter(km.cluster_centers_[:, 0], km.cluster_centers_[:, 1], marker='x', s=100, c='r')
print(km.labels_)
plt.show()

dict = {}
unique_ids['cluster'] = km.labels_
for cluster in range(10):
    arr = unique_ids[unique_ids['cluster'] == cluster]['id'].reset_index(drop=True)
    dict[cluster] = arr.to_dict()
dict = json.dumps(dict)
print(dict)
# for i in range(len(km.labels_)):
#     mydict[i] = 0

# print(km.labels_)
# Transform the dictionary into list
# for i in range(len(km.labels_)):
#     mydict.append([id_columns[i], km.labels_[i]])
# print(mydict)
