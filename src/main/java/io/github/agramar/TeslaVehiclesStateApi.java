package io.github.agramar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.agramar.model.ChargeState;
import io.github.agramar.model.TeslaResponse;
import io.github.agramar.model.Vehicle;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static io.github.agramar.util.HttpUtils.STRING_RESPONSE_HANDLER;

@Slf4j
public class TeslaVehiclesStateApi {

    private final HttpRequest.Builder requestBuilder;
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final ObjectMapper objectMapper = JsonMapper.builder()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .findAndAddModules()
        .build();

    public TeslaVehiclesStateApi(String accessToken) {
        this.requestBuilder = HttpRequest.newBuilder()
            .header("Authorization", "Bearer " + accessToken);
    }

    private HttpRequest buildHttpRequest(Long id, String apiPath) {
        return requestBuilder
            .uri(URI.create("https://owner-api.teslamotors.com/api/1/vehicles/" + id + "/" + apiPath))
            .GET()
            .build();
    }

    public TeslaResponse<Vehicle> getVehicleDate(Long id) throws Exception {
        HttpRequest request = buildHttpRequest(id, "vehicle_data");
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

    public TeslaResponse<ChargeState> getChargeState(Long id) throws Exception {
        HttpRequest request = buildHttpRequest(id, "data_request/charge_state");
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


    public TeslaResponse<Boolean> getMobileEnabled(Long id) throws Exception {

        HttpRequest request = buildHttpRequest(id, "mobile_enabled");
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
