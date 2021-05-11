package baml.urlshortner.service;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlShortenerService {
    private ConcurrentHashMap<String, String> store;

    /**
     * Given a good amount of time, I would have used a caching framework such as Radis or GemFire with Databsae to
     * quickly fetch the original URL from hashcode. For now using temporary solution.
     */
    public UrlShortenerService(){
        store = new ConcurrentHashMap();
    }
    public String convertToShortUrl(String requestedUri, String longUrl) {
        System.out.println("Inside Service");
        final String id = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
        store.put(id, longUrl);
        return new StringBuffer().append(requestedUri).append("/api/v1/longurl?id=").append(id).toString();
    }

    /**
     * Reurn value from temporary cache
     * @param id the hashcode or tiny URL
     * @return the original long URL.
     */
    public String convertToLongUrl(String id){
        return store.get(id);
    }
}
