### Short URL Project

This is the simple project which produces shorter url of given original url. 

In hosts file of your operating system, bind the 127.0.0.1 to your custom short url domain which can be set in application.properties. By default, I'm using gnl.co 
as short url domain. 

By default, the project doesn't use any database, it uses a hashmap which stays in RAM. 

The application runs on **8282** port. You can change the port in application.properties file (*server.port* property) to whatever you want.

You can get short url of your original like this with CURL: 

curl -i -X POST -H 'Content-Type: application/json' -d 'https://google.com' http://localhost:8282/url/shorten/

You will get a response like this: 

gnl.co:8282/1688907962275

When you try to go to above link, you will be redirected to google.com immediately. 