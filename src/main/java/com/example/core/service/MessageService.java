package com.example.core.service;

import com.example.core.drivingPort.IMessageService;
import com.example.domain.Message;
import com.example.domain.MyAppProperties;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageService implements IMessageService {

    private final MyAppProperties _appProperties;

    MessageService(MyAppProperties appProperties) {
        _appProperties = appProperties;    
    }
    
    @Override
    public Message getMessage(String content) {

        HttpClient client = HttpClient.newHttpClient();

        URI httpURI;
        try {
            httpURI = new URI("http://jsonplaceholder.typicode.com/posts/1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder(httpURI).build();

        CompletableFuture<String> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                /*.thenAccept(resp -> {
                    System.out.println("Got pushed response " + resp.uri());
                    System.out.println("Response status code: " + resp.statusCode());
                    System.out.println("Response body: " + resp.body());
                })*/
                .thenApply(rest -> rest.body());

        var result = futureResponse.join();

        System.out.println("futureResponse is " + futureResponse);

        System.out.println("after future response: " + result);

        return new Message(String.format("%s %s %s",
                _appProperties.getName(),
                _appProperties.getVersion(),
                content));
    }
}
