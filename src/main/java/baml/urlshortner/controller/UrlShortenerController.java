package baml.urlshortner.controller;

import baml.urlshortner.model.UserRequest;
import baml.urlshortner.service.UrlShortenerService;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping ("/api/v1")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;

    @GetMapping("/")
    @PostMapping("/shorturl")
    public String tinyUrl(HttpServletRequest httpRequest, UserRequest request) {
        System.out.println("Request received to shorten URL. The original URL is "+request.getUrl());
        String requestUrl = httpRequest.getRequestURL().toString();

        //TODO: this should not use hardcoded string.
        String requestedUri = requestUrl.substring(0, requestUrl.indexOf(httpRequest.getRequestURI(), "http://".length()));
        return service.convertToShortUrl(requestedUri, request.getUrl());
    }

    @GetMapping("/longurl")
    public void originalUrl(@RequestParam String id, HttpServletResponse response) throws Exception{
        System.out.println("Request received to redirect to original url from short url. The hashcode for shorten url is "+id);
        final String longUrl = service.convertToLongUrl(id);

        // if hashcode found in cache then redirect to the site else show error.
        if (longUrl != null) {
            System.out.println("Shorten URL found in local cache. Now, redirecting to original URL.");
            response.addHeader("Location", longUrl);
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            System.out.println("Oops, someone messed up with shorten URL. Please make sure to call correct shorten URL.");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
