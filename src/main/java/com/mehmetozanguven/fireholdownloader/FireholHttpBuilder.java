package com.mehmetozanguven.fireholdownloader;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public interface FireholHttpBuilder {

    HttpClient.Builder getHttpClientBuilder();

    HttpRequest.Builder getHttpRequestBuilder();
}
