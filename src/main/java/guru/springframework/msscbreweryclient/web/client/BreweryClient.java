package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public BeerDto getBeerById(UUID beerId) {
        String url = apiHost+BEER_PATH_V1+beerId.toString();
        return restTemplate.getForObject(url, BeerDto.class);
    }

    public URI createBeer(BeerDto beerDto) {
        String url = apiHost+BEER_PATH_V1;
        System.out.println("Creating " + beerDto);
        System.out.println("URL = " + url);
        return restTemplate.postForLocation(url, beerDto);
    }

    public void updateBeer(UUID beerId, BeerDto beerDto) {
        String url = apiHost+BEER_PATH_V1+"/"+beerId.toString();
        System.out.println("Updating " + beerDto);
        System.out.println("URL = " + url);
        restTemplate.put(url, beerDto);
    }

    public void deleteBeer(UUID beerId) {
        String url = apiHost+BEER_PATH_V1+"/"+beerId.toString();
        System.out.println("Deleting " + beerId);
        System.out.println("URL = " + url);
        restTemplate.delete(url);
    }
}
