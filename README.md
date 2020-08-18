# BE



Add Swagger UI for API Documentation

Running Application

Send requset with Postman
http://localhost:8080/v2/api-docs 

Access with Swagger UI interface
http://localhost:8080/swagger-ui.html


Elastic Search 

endpoint: https://d6cf1b4217804bcabcf434eb25691c3d.us-east-1.aws.found.io:9243

Kibana 

endpoint: https://129be6d310594568b160812455e1af0f.us-east-1.aws.found.io:9243


You can leverage two authentication mechanisms to reach your Elasticsearch cluster, API Key and basic authentication:
API key
```java
RestHighLevelClient client = new RestHighLevelClient(
    RestClient.builder("<CloudID>")
    .setDefaultHeaders(
        new Header[]{new BasicHeader("Authorization", "Bearer " + "<base64-encoded-APIKey>")});
MainResponse response = client.info(RequestOptions.DEFAULT));
```

Basic authentication
```java
final CredentialsProvider credentialsProvider =
    new BasicCredentialsProvider();
credentialsProvider.setCredentials(AuthScope.ANY,
    new UsernamePasswordCredentials("<Username>", "<Password>"));
RestHighLevelClient client = new RestHighLevelClient(
    RestClient.builder("<CloudID>")
    .setHttpClientConfigCallback(new HttpClientConfigCallback() {
        @Override
        public HttpAsyncClientBuilder customizeHttpClient(
                HttpAsyncClientBuilder httpClientBuilder) {
            return httpClientBuilder
                .setDefaultCredentialsProvider(credentialsProvider);
        }
    }));
MainResponse response = client.info(RequestOptions.DEFAULT);
```
