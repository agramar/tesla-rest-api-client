package io.github.agramar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agramar.model.UserProfile;
import io.github.agramar.model.UserVaultProfile;
import io.github.agramar.model.response.TeslaRestResponse;
import io.github.agramar.util.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class TeslaUserApi {

    private final String BASE_URL = "https://owner-api.teslamotors.com/api/1/users";
    private final HttpClient httpClient;
    private final HttpRequest.Builder requestBuilder;
    private final ObjectMapper mapper;


    public TeslaUserApi(String accessToken) {
        this.httpClient = HttpClient.newBuilder().build();
        this.requestBuilder = HttpRequest.newBuilder().header("Authorization", "Bearer " + accessToken);
        this.mapper = ObjectMapperHolder.INSTANCE.get();
    }

    public TeslaRestResponse<UserProfile> getMe() throws Exception {

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

        return mapper.readValue(responseBody, new TypeReference<>() {
        });
    }

    public TeslaRestResponse<UserVaultProfile> getVaultProfile() throws Exception {

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

        return mapper.readValue(responseBody, new TypeReference<>() {
        });
    }
}
