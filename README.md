# baml-url
URL shortener project


Start the service
>docker-compose up

Call API to shorten the URL by passing URL
>curl -d "url={url to shorten}" -X POST http://localhost:8080/api/v1/shorturl

For example 
>curl -d "url=https://www.bofaml.com/content/boaml/en_us/home.html" -X POST http://localhost:8080/api/v1/shorturl

Check the output and Open the output URL.
http://localhost:8080/api/v1/longUrl?id=<the hash code>

For example - this will redirect to original URL. 
>http://localhost:8080/api/v1/longUrl?id=3495e783