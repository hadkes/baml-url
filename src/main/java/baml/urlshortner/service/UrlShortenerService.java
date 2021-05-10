package baml.urlshortner.service;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlShortenerService {
    private ConcurrentHashMap<String, String> store;

    public UrlShortenerService(){
        store = new ConcurrentHashMap();
    }
    public String convertToShortUrl(String requestedUri, String longUrl) {
        System.out.println("Inside Service");
        final String id = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
        store.put(id, longUrl);
        return new StringBuffer().append(requestedUri).append("/api/v1/longUrl?id=").append(id).toString();
    }

    public String convertToLongUrl(String id){
        return store.get(id);
    }
}
