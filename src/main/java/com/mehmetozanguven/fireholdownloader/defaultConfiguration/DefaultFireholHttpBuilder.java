package com.mehmetozanguven.fireholdownloader.defaultConfiguration;

import com.mehmetozanguven.fireholdownloader.FireholHttpBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DefaultFireholHttpBuilder implements FireholHttpBuilder {

    @Override
    public HttpClient.Builder getHttpClientBuilder() {
        return HttpClient.newBuilder();
    }

    @Override
    public HttpRequest.Builder getHttpRequestBuilder() {
        return HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                ;
    }
}
