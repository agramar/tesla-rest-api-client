package io.github.agramar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agramar.model.ChargeState;
import io.github.agramar.model.Vehicle;
import io.github.agramar.model.response.TeslaRestResponse;
import io.github.agramar.util.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Slf4j
public class TeslaVehiclesStateApi {

    private final HttpClient httpClient;
    private final HttpRequest.Builder requestBuilder;
    private final ObjectMapper mapper;

    public TeslaVehiclesStateApi(String accessToken) {
        if (accessToken == null || accessToken.isBlank())
            throw new IllegalArgumentException("access token is blank");

        this.httpClient = HttpClient.newBuilder().build();
        this.requestBuilder = HttpRequest.newBuilder().header("Authorization", "Bearer " + accessToken);
        this.mapper = ObjectMapperHolder.INSTANCE.get();
    }

    private HttpRequest buildHttpRequest(Long id, String apiPath) {
        return requestBuilder
            .uri(URI.create("https://owner-api.teslamotors.com/api/1/vehicles/" + id + "/" + apiPath))
            .GET()
            .build();
    }

    public TeslaRestResponse<Vehicle> getVehicleDate(Long id) throws Exception {
        HttpRequest request = buildHttpRequest(id, "vehicle_data");
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

    public TeslaRestResponse<ChargeState> getChargeState(Long id) throws Exception {
        HttpRequest request = buildHttpRequest(id, "data_request/charge_state");
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


    public TeslaRestResponse<Boolean> getMobileEnabled(Long id) throws Exception {

        HttpRequest request = buildHttpRequest(id, "mobile_enabled");
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
