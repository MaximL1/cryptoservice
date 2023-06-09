# cryptoservice
crypto task

1. gradle build
2. docker-compose up --build
3. Import collection in postman for add new crypto (add ETH LTC BTC XRP DOGE or just 4 for checking importing restriction):
   {
   "info": {
   "_postman_id": "706a4062-f122-4501-a473-90b12a28f035",
   "name": "crypto",
   "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
   "_exporter_id": "15107566"
   },
   "item": [
   {
   "name": "addCrypto",
   "request": {
   "auth": {
   "type": "noauth"
   },
   "method": "POST",
   "header": [],
   "url": {
   "raw": "localhost:8080/api/v1/crypto/addCrypto?crypto=ETH",
   "host": [
   "localhost"
   ],
   "port": "8080",
   "path": [
   "api",
   "v1",
   "crypto",
   "addCrypto"
   ],
   "query": [
   {
   "key": "crypto",
   "value": "ETH"
   }
   ]
   }
   },
   "response": []
   },
   {
   "name": "importCrypto",
   "request": {
   "auth": {
   "type": "noauth"
   },
   "method": "POST",
   "header": [],
   "body": {
   "mode": "formdata",
   "formdata": [
   {
   "key": "file",
   "type": "file",
   "src": "/C:/Users/Lenovo/Downloads/Prices/BTC_values.csv"
   }
   ]
   },
   "url": {
   "raw": "localhost:8080/api/v1/crypto/import",
   "host": [
   "localhost"
   ],
   "port": "8080",
   "path": [
   "api",
   "v1",
   "crypto",
   "import"
   ]
   }
   },
   "response": []
   }
   ]
   }
4. Import file with crypto to endpoint importCrypto from collection:
5. Test all endpoints
 
