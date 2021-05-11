package baml.urlshortner.model;

/**
 * Model class to store user input request, especially the URL to shorten.
 */
public class UserRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
