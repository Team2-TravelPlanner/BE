package com.laioffer.travelplanner.configurations;


import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.metrics.ParsedSingleValueNumericMetricsAggregation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableElasticsearchRepositories(basePackages = "com.laioffer.travelplanner.repositories")
public class ElasticSearchConfig {
    @Value("${elastic.username}")
    private String username;

    @Value("${elastic.password}")
    private String password;

    @Value("${elastic.id}")
    private String cloudId;



    @Bean
    public RestHighLevelClient client() {
//        ClientConfiguration clientConfiguration
//                = ClientConfiguration.builder()
//                .connectedTo("https://d6cf1b4217804bcabcf434eb25691c3d.us-east-1.aws.found.io:9243")
//                .build();
//
//        final RestHighLevelClient rest = RestClients.create(clientConfiguration).rest();
//        return rest;

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder("travelplanner:dXMtZWFzdC0xLmF3cy5mb3VuZC5pbyRkNmNmMWI0MjE3ODA0YmNhYmNmNDM0ZWIyNTY5MWMzZCQxMjliZTZkMzEwNTk0NTY4YjE2MDgxMjQ1NWUxYWYwZg==").setDefaultHeaders
//                        (new Header[]{new BasicHeader("Authorization", "Bearer " + "YzJqLWg2ZTRUY1MxSTc2akZSVGc5dw==")}));


        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(cloudId).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)));

        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

}
