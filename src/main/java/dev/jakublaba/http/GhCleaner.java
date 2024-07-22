package dev.jakublaba.http;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import dev.jakublaba.model.GhCleanerConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

@RequiredArgsConstructor
@Builder
public class GhCleaner {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String REQUEST_URL_TEMPLATE = "https://api.github.com/repos/%s/%s";
    private static final String ACCEPT_HEADER_VALUE = "application/vnd.github+json";
    private static final String AUTH_HEADER_TEMPLATE = "Bearer %s";
    private final GhCleanerConfig config;

    public void clean() {
        var authHeaderValue = String.format(AUTH_HEADER_TEMPLATE, config.authToken());
        System.out.println("Deleting repos...");
        for (var repo : config.repos()) {
            var url = String.format(REQUEST_URL_TEMPLATE, config.username(), repo);
            var req = HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create(url))
                    .header("Accept", ACCEPT_HEADER_VALUE)
                    .headers("Authorization", authHeaderValue)
                    .build();
            CLIENT.sendAsync(req, HttpResponse.BodyHandlers.ofInputStream())
                    .thenAccept(res -> System.out.println(repo))
                    .join();
        }
    }

    public void addReposToBatch(String... repos) {
        config.repos().addAll(Arrays.asList(repos));
    }

    public void removeReposFromBatch(String... repos) {
        config.repos().removeAll(Arrays.asList(repos));
    }

    public void clearBatch() {
        config.repos().clear();
    }
}
