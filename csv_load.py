import calendar
import glob
import json
import os
import sys

import pandas as pd

arr = ['lat', 'lon', 'pressure', 'temperature', 'humidity']


def set_values(id, data, time):
    id = str(int(id))
    newid = None
    for i in range(len(kluster)):
        if id in kluster[i]['sources']:
            newid = kluster[i]['sources'].index(id)
            break
    if newid is None:
        print(id)
        return
    for j in data.keys():
        values[newid]['time'] = time
        values[newid][j] += data[j]
    done[newid] += 1


def prepare_reader(reader):
    if 'pressure' not in reader.columns:
        reader['pressure'] = 0
    if 'temperature' not in reader.columns:
        reader['temperature'] = 0
    if 'humidity' not in reader.columns:
        reader['humidity'] = 0
    reader = reader.drop(
        ['sensor_type', 'location', 'timestamp', 'altitude', 'pressure_sealevel', 'P1', 'P2',
         'durP1', 'ratioP1', 'durP2', 'ratioP2', 'noise_LAeq', 'noise_LA_min', 'noise_LA_max', 'noise_LA01',
         'noise_LA95'], errors='ignore',
        axis=1)
    reader.fillna(0)
    reader = reader.replace('unknown', 0)
    reader = reader.replace('unavailable', 0)
    reader = reader.replace('xx', 0)
    reader = reader.replace('XX', 0)
    reader = reader.replace('b', 0)
    reader = reader.astype(float)
    return reader


temperature_extreme = [
    [-12.3, -6.3, -9.3],
    [-11.1, -4.2, -7.7],
    [-5.6, 1.5, -2.2],
    [1.7, 10.4, 5.8],
    [7.6, 18.4, 13.1],
    [11.5, 21.7, 16.6],
    [13.5, 23.1, 18.2],
    [12.0, 21.5, 16.4],
    [7.1, 15.4, 11.0],
    [2.1, 8.2, 5.1],
    [-3.3, 1.1, -1.2],
    [-8.6, -3.5, -6.1]
]
humidity_extreme = 0


def extreme(data, month):
    month = int(month)
    month -= 1
    if (data['temperature'] < temperature_extreme[month][0]) & (
            data['temperature'] > temperature_extreme[month][1]):
        data['temperature'] = temperature_extreme[month][2]
    return data



paths = []
kluster = json.load(open("kluster.json", "r"))
kluster_num = len(kluster)
list_of_df = []
for i in range(kluster_num):
    list_of_df.append(pd.DataFrame(columns=arr))


def days_in_month(year, month):
    return calendar.monthrange(year, month)[1]


for year in range(2023, 2025):
    if year == 2024:
        month_num = 4
    else:
        month_num = 12
    for month in range(1, month_num + 1):
        for day in range(1, days_in_month(year, month)):
            day = str(day).zfill(2)
            path = os.path.join(sys.path[0], "csv", str(year), str(month).zfill(2), day)

            csv_files = glob.glob(os.path.join(path, "*.csv"))
            done = {}
            values = []
            for o in range(0, kluster_num):
                values.append({
                    'lat': 0,
                    'lon': 0,
                    'pressure': 0,
                    'temperature': 0,
                    'humidity': 0,
                    'time': 0
                })
                done[o] = 0
            new = str(year) + "-" + str(month).zfill(2) + "-" + day
            print(new)
            for f in csv_files:
                reader = pd.read_csv(f, sep=";")
                id = f.split("\\")[-1].split(".")[0]
                id = float(id)
                reader = prepare_reader(reader)
                center = (reader[reader.sensor_id == id].drop(['sensor_id'], axis=1, errors='ignore'))
                center = center.mean()
                center = extreme(center, month)
                center = center.to_dict()
                if center['pressure'] == 0 and center['temperature'] == 0 and center['humidity'] == 0:
                    os.remove(f)
                    continue
                if len(center) != 0:
                    set_values(id, center, new)
                del center

                del reader
            for district_id in range(len(values)):
                district = values[district_id]
                if done[district_id] != 0:
                    district = [district[x] / done[district_id] for x in district if x != 'time']
                    district = pd.DataFrame([district], columns=arr, index=[new])
                    list_of_df[district_id] = pd.concat([list_of_df[district_id], district], axis=0)
for df_i in range(kluster_num):
    df = list_of_df[df_i]
    df.to_csv('data/' + str(df_i+1) + '.csv', index=True)