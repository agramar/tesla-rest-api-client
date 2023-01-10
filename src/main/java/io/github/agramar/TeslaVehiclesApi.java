package io.github.agramar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.agramar.model.TeslaResponse;
import io.github.agramar.model.Vehicle;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static io.github.agramar.util.HttpUtils.STRING_RESPONSE_HANDLER;

@Slf4j
public class TeslaVehiclesApi {

    private final String accessToken;
    private final HttpRequest.Builder requestBuilder;
    private final String BASE_URL = "https://owner-api.teslamotors.com/api/1/vehicles";
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    public TeslaVehiclesApi(String accessToken) {
        this.accessToken = accessToken;
        this.requestBuilder = HttpRequest.newBuilder()
            .header("Authorization", "Bearer " + accessToken);
    }

    public TeslaResponse<List<Vehicle>> getVehicles() throws Exception {
        if (accessToken == null || accessToken.isBlank())
            throw new IllegalArgumentException("access token is blank");

        HttpRequest request = requestBuilder
            .uri(URI.create(BASE_URL))
            .GET()
            .build();
        log.trace("request : {}", request);

        HttpResponse<String> response = httpClient.send(request, STRING_RESPONSE_HANDLER);
        log.trace("response : {}", response.body());

        String responseBody = response.body();
        log.trace("response body : {}", responseBody);

        if (response.statusCode() != 200)
            throw new Exception("http request fail : " + response.statusCode());

        return objectMapper.readValue(responseBody, new TypeReference<>() {
        });
    }

    public TeslaResponse<Vehicle> getVehicle(long id) throws Exception {
        if (accessToken == null || accessToken.isBlank())
            throw new IllegalArgumentException("access token is blank");

        HttpRequest request = requestBuilder
            .uri(URI.create(BASE_URL + "/" + id))
            .GET()
            .build();
        log.trace("request : {}", request);

        HttpResponse<String> response = httpClient.send(request, STRING_RESPONSE_HANDLER);
        log.trace("response : {}", response.body());

        String responseBody = response.body();
        log.trace("response body : {}", responseBody);

        if (response.statusCode() != 200)
            throw new Exception("http request fail : " + response.statusCode());

        return objectMapper.readValue(responseBody, new TypeReference<>() {
        });
    }
}
