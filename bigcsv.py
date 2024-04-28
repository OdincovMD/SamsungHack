import calendar
import glob
import json
import urllib
import urllib.request
from pathlib import Path

from requests_futures.sessions import FuturesSession

session = FuturesSession()

sessions = []
json_url = 'https://data.sensor.community/airrohr/v1/filter/country=RU;area=55.7522,37.6156,14'

response = urllib.request.urlopen(json_url)
data = json.loads(response.read())
unique_ids = []
for year in range(2023, 2025):
    for month in range(1, 13):
        for day in range(1, 32):
            ids = glob.glob('C:/Users/ignat/PycharmProjects/eco/csv/2023/' + str(month) + '/' + str(day) + '/*.csv')
            for i in range(len(ids)):
                id = ids[i]
                id = Path(id).stem
                unique_ids.append(id)
                # a.append()
unique_ids = set(unique_ids)

def days_in_month(year, month):
    return calendar.monthrange(year, month)[1]

# @functools.lru_cache()
def get_urls():
    for i in range(0, len(data)):
        sensor = data[i]['sensor']
        id = sensor['id']
        if id in ids:
            continue
        type = sensor['sensor_type']['name'].lower()
        if type == 'dnms (laerm)':
            type = 'laerm'
        if type == 'sds011':
            continue
        for year in range(2024, 2025):
            if year == 2024:
                month_num = 4
            else:
                month_num = 12
            for month in range(1, month_num + 1):
                for day in range(1, days_in_month(year, month)):
                    id = sensor['id']
                    if str(id) in unique_ids:
                        continue
                    date = str(year) + '-' + str(month).zfill(2) + '-' + str(day).zfill(2)
                    # print('https://archive.sensor.community/'+date+'/'+date+'_'+type+'_sensor_'+str(id)+'.csv')
                    # https://archive.sensor.community/2024-04-26/2024-04-26_sds011_sensor_261.csv
                    url = 'https://archive.sensor.community/' + date + '/' + date + '_' + type + '_sensor_' + str(
                        id) + '.csv'
                    try:
                        print(url)
                        urllib.request.urlretrieve(url,
                                                   'C:/Users/ignat/PycharmProjects/eco/csv/' + str(year) + '/' + str(
                                                       month).zfill(2) + '/' + str(day).zfill(2) + '/' + str(
                                                       id) + '.csv')
                    except urllib.request.HTTPError:
                        print('\n')
                        continue

get_urls()
