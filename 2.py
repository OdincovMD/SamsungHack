import calendar
import json
import urllib
import urllib.request

kluster = json.load(open("kluster.json", "r"))
unique_ids = kluster[7]['sources']

def days_in_month(year, month):
    return calendar.monthrange(year, month)[1]

# @functools.lru_cache()

data = json.load(open("C:/Users/ignat/PycharmProjects/eco/data/file.json", "r"))
def get_urls():
    for i in range(0, len(data)):
        sensor = data[i]['sensor']
        id = sensor['id']
        type = sensor['sensor_type']['name'].lower()
        if type != 'bme280':
            continue
        print(type)
        for year in range(2023, 2025):
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
        # date = '2024-04-26'
        # https://archive.sensor.community/2024-04-26/2024-04-26_sds011_sensor_261.csv
        # urls[i] = 'https://archive.sensor.community/'+date+'/'+date+'_'+type+'_sensor_'+str(id)+'.csv'
    # print(urls)


get_urls()









# "boundary": [ “37.394,55.8787”, “37.394, 55.7687”,”37.492,55.7687”,”37.524,55.8487”,” 37.524,55.8787”]
# "boundary": [” 37.524,55.8787”, ”37.524,55.8487”,”37.594,55.8187”,”37.644,55.7987”, “37.724,55.8787”] },
# "boundary": [“37.724,55.8787”, “37.644,55.7987”,” 37.7240,55.7287”,”37.8270,55.8187”},
# "boundary": [“37.8270,55.8187”, “37.7240,55.7287”,”37.8270,55.6687”},
# "boundary": [“37.8270,55.6687”, “37.7240,55.7287”,” 37.644,55.6187”},
# "boundary": [“37.644,55.6187”, “37.5440,55.7187”,” 37.514,55.6187”},
# "boundary": [“37.514,55.6187”, “37.5440,55.7187”,” 37.492,55.7687”, “37.394, 55.7687”},
# "boundary": [“37.492,55.7687”, “37.524,55.8487”,” 37.594,55.8187”, “37.644,55.7987”,” 37.5440,55.7187”},
# "boundary": [“37.644,55.7987”,” 37.7240,55.7287”,” 37.644,55.6187”,” 37.644,55.7987”},
# "boundary": [“37.644,55.7987”,” 37.644,55.6187”,” 37.5440,55.7187”,” 37.644,55.7987”},