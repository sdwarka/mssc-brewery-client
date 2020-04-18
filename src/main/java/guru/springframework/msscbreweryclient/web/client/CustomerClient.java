package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {

    public String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    //enable injection thru application.properties
    private String apiHost;
    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    //enable creation of the RestTemplate via injected builder
    private RestTemplate restTemplate;
    public CustomerClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    //method to invoke retrieve customer
    public CustomerDto getCustomerById(UUID custId) {
        String url = apiHost + CUSTOMER_PATH_V1 + custId.toString();
        System.out.println("Getting customer " + custId);
        System.out.println("Invoking URL " + url);
        return restTemplate.getForObject(url, CustomerDto.class);
    }

    //method to invoke create customer
    public URI createCustomer(CustomerDto customerDto) {
        String url = apiHost + CUSTOMER_PATH_V1;
        System.out.println("Creating " + customerDto);
        System.out.println("Invoking URL " + url);
        return restTemplate.postForLocation(url, customerDto);
    }

    //method to invoke update customer
    public void updateCustomer(UUID custId, CustomerDto custDto) {
        String url = apiHost + CUSTOMER_PATH_V1 + custId.toString();
        System.out.println("updating customer " + custId);
        System.out.println("Invoking URL " + url);
        restTemplate.put(url, custDto);
    }

    //method to invoke delete customer
    public void deleteCustomer(UUID custId) {
        String url = apiHost + CUSTOMER_PATH_V1 + custId.toString();
        System.out.println("deleting customer " + custId);
        System.out.println("Invoking URL " + url);
        restTemplate.delete(url, custId);
    }
}
