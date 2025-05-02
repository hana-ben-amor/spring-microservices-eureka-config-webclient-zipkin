package com.example.department_service.config;

import com.example.department_service.client.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient employeeWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083")
                .build();
    }

    @Bean
    public EmployeeClient employeeClient(@Qualifier("employeeWebClient") WebClient employeeWebClient) {
        // Create the WebClientAdapter using the provided WebClient instance
        WebClientAdapter adapter = WebClientAdapter.create(employeeWebClient);

        // Create the HttpServiceProxyFactory instance with the adapter
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        // Create and return the EmployeeClient proxy
        return factory.createClient(EmployeeClient.class);
    }


}
