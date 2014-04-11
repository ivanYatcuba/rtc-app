package net.github.rtc.app.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract Data Access Object class
 * This is parent class to custom DAO
 *
 * @author Vladislav Pikus
 */
public abstract class AbstractResource {
    protected RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected String hostUrl;

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getHostUrl() {
        return hostUrl;
    }
}
