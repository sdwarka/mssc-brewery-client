package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerClientTest {

    @Autowired
    private CustomerClient client;

    @Test
    public void testGetCustomerById() {
        CustomerDto custDto = client.getCustomerById(UUID.randomUUID());
        assertNotNull(custDto);
        System.out.println("Customer = " + custDto);
    }

    @Test
    public void testCreateCustomer() {
        CustomerDto custDto = CustomerDto.builder().name("New Customer").build();
        URI location = client.createCustomer(custDto);
        assertNotNull(custDto);
        System.out.println("Customer URI = " + location);
    }

    @Test
    public void testUpdateCustomer() {
        CustomerDto custDto = CustomerDto.builder().name("New Customer").build();
        client.updateCustomer(UUID.randomUUID(), custDto);
    }

    @Test
    public void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}
