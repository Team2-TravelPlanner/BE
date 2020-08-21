package com.laioffer.travelplanner.configurations;


import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableElasticsearchRepositories(basePackages = "com.laioffer.travelplanner.repositories")
public class ElasticSearchConfig {
//    @Value("${elasticCloud.username}")
//    private static String username;
//
//    @Value("${elasticCloud.password}")
//    private static String password;
//
//    @Value("${elasticCloud.id}")
//    private static String cloudId;


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
                new UsernamePasswordCredentials("elastic", "G5UXS6jJAIo9VeewNum39mUF"));

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder("travelplanner:dXMtZWFzdC0xLmF3cy5mb3VuZC5pbyQyMWJkY2UxMzJjZmQ0NWY1OGUwNzQyOTE0NTNjYjgxZCRjOWY2NmE4ODllMDY0OWMzOTQ5NzljM2Y2NTQ5YWQ4MA==" + 
                		"").setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)));

        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}
