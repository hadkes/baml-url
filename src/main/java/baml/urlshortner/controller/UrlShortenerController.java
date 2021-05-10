package baml.urlshortner.controller;

import baml.urlshortner.model.UserRequest;
import baml.urlshortner.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping ("/api/v1")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;

    @GetMapping("/")
    public String info(){
        return "Usage: \n" +
                "\t 1. Call /api/v1/shortUrl using POST by sending long URL as url." +
                "\t 2. Call output given by previous call and that will redirect to original long url.";
    }

    @PostMapping("/shortUrl")
    public String tinyUrl(HttpServletRequest httpRequest, UserRequest request) {
        System.out.println("Inside controller. Method tinyUrl is called.");
        String requestUrl = httpRequest.getRequestURL().toString();
        String requestedUri = requestUrl.substring(0, requestUrl.indexOf(httpRequest.getRequestURI(), "http://".length()));
        return service.convertToShortUrl(requestedUri, request.getUrl());
    }

    @GetMapping("/longUrl")
    public void originalUrl(@RequestParam String id, HttpServletResponse response) throws Exception{
        System.out.println("Inside controller. Method originalUrl is called.");
        final String longUrl = service.convertToLongUrl(id);
        if (longUrl != null) {
            response.addHeader("Location", longUrl);
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
