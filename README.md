# Простенькое API Страны

## Сущности
###### Country - Страна
* Long id - ключ
* String title - наименование
* List<City> cities - города в этой стране

###### City - Город
* Long id - ключ
* String title - наименование

## API

* GET /city - список городов 200
* GET /city/:id - поиск города 200
* GET /city/?country_id=:id - поиск по стране 200
* POST /city - создать 201
* PUT /city - изменить 200


* GET /country - список стран 200
* GET /country/:id - поиск страны 200
* GET /country/?city_id=:id - поиск по городу 200
* POST /country - создать 201
* PUT /country - изменить 200

Сущность не найдена - 404
