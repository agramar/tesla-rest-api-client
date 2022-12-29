package io.github.agramar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agramar.model.TeslaResponse;
import io.github.agramar.model.TeslaUserProfile;
import io.github.agramar.model.TeslaUserVaultProfile;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class TeslaUserApi {

    private final String accessToken;
    private final String BASE_URL = "https://owner-api.teslamotors.com/api/1/users";
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final HttpRequest.Builder requestBuilder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TeslaUserApi(String accessToken) {
        this.accessToken = accessToken;
        this.requestBuilder = HttpRequest.newBuilder()
            .header("Authorization", "Bearer " + accessToken);
    }

    public TeslaResponse<TeslaUserProfile> getMe() throws Exception {

        if (accessToken == null || accessToken.isBlank())
            throw new IllegalArgumentException("access token is blank");

        HttpRequest request = requestBuilder
            .uri(URI.create(BASE_URL + "/me"))
            .GET()
            .build();
        log.trace("request : {}", request);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.trace("response : {}", response.body());

        String responseBody = response.body();
        log.trace("response body : {}", responseBody);

        if (response.statusCode() != 200)
            throw new Exception("http request fail : " + response.statusCode());

        return objectMapper.readValue(responseBody, new TypeReference<>() {});
    }

    public TeslaResponse<TeslaUserVaultProfile> getVaultProfile() throws Exception {

        if (accessToken == null || accessToken.isBlank())
            throw new IllegalArgumentException("access token is blank");

        HttpRequest request = requestBuilder
            .uri(URI.create(BASE_URL + "/vault_profile"))
            .GET()
            .build();
        log.trace("request : {}", request);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.trace("response : {}", response.body());

        String responseBody = response.body();
        log.trace("response body : {}", responseBody);

        if (response.statusCode() != 200)
            throw new Exception("http request fail : " + response.statusCode());

        return objectMapper.readValue(responseBody, new TypeReference<>() {});
    }
}
