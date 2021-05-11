# URL Shortener project
The simple URL shortener project using Google Guava library. 

To start this service, follow these steps
>git clone https://github.com/hadkes/baml-url.git </br>
>cd baml-url</br>
>docker-compose up </br>

To run this service, follow these steps
>curl -d "url={url to shorten}" -X POST http://localhost:8080/api/v1/shorturl </br>
>curl -d "url=https://www.irishtimes.com/" -X POST http://localhost:8080/api/v1/shorturl</br>

Check the output and Open the output URL in browser.</br>
>http://localhost:8080/api/v1/longUrl?id=<tiny_url> </br>
>http://localhost:8080/api/v1/longUrl?id=3495e783 </br>
 

<P>
With more time following can be done
</br>1. At present, volatile persistence is used. In future, it can be replaced with caching frameworks
such as Radis or GemFire with Database.
</br>2. Third party library is used to generate tiny URL, other options, such as hashcode or UUID 
can be explored.
</br>3. Extensive unit and mock test cab be written. 
</P>