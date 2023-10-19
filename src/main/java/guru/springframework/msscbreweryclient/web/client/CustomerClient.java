package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "customer.brewery", ignoreUnknownFields = false)
public class CustomerClient {

    private RestTemplate restTemplate;

    private String apihost;

    private final String CUSTOMER_V1_PATH = "/api/v1/customer/";

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apihost + CUSTOMER_V1_PATH + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apihost + CUSTOMER_V1_PATH, customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto){
        restTemplate.put(apihost + CUSTOMER_V1_PATH + uuid, customerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apihost + CUSTOMER_V1_PATH + uuid);
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
