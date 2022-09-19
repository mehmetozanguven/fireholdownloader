package io.github.mehmetozanguven;

import io.github.mehmetozanguven.defaultConfiguration.DefaultFireholHttpBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

/**
 * To customize the client and request, use that interface
 * For instance, if you want to change request timeout (by default, Duration.of(10, ChronoUnit.SECONDS)), you can use FireholHttpBuilder
 * @see DefaultFireholHttpBuilder for the default implementation
 */
public interface FireholHttpBuilder {

    HttpClient.Builder getHttpClientBuilder();

    HttpRequest.Builder getHttpRequestBuilder();
}
