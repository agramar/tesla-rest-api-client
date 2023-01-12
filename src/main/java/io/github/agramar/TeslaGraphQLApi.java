package io.github.agramar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agramar.model.ChargingWrapper;
import io.github.agramar.model.request.ChargingSitePricing;
import io.github.agramar.model.request.NearbyChargingSitesArgs;
import io.github.agramar.model.request.TeslaGraphQLRequest;
import io.github.agramar.model.response.TeslaGraphQLResponse;
import io.github.agramar.util.MediaType;
import io.github.agramar.util.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class TeslaGraphQLApi {

    private final String BASE_URL = "https://akamai-apigateway-charging-ownership.tesla.com/graphql";
    private final HttpClient httpClient;
    private final HttpRequest.Builder requestBuilder;
    private final ObjectMapper mapper;

    public TeslaGraphQLApi(String accessToken) {
        this.httpClient = HttpClient.newBuilder().build();
        this.requestBuilder = HttpRequest.newBuilder()
            .header("Authorization", "Bearer " + accessToken)
            .headers("Content-Type", MediaType.APPLICATION_JSON.getValue());
        this.mapper = ObjectMapperHolder.INSTANCE.get();
    }

    public TeslaGraphQLResponse<ChargingWrapper> getNearbyChargingSites(TeslaGraphQLRequest<NearbyChargingSitesArgs> nearbyChargingSitesArgsTeslaGraphQLRequest) throws Exception {

        String requestBodyString = mapper.writeValueAsString(nearbyChargingSitesArgsTeslaGraphQLRequest);

        HttpRequest request = requestBuilder
            .uri(URI.create(BASE_URL))
            .POST(HttpRequest.BodyPublishers.ofString(requestBodyString))
            .build();
        log.trace("request : {} / {}", request, requestBodyString);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.trace("response : {} / {}", response.statusCode(), response.body());

        if (response.statusCode() != 200)
            throw new Exception("http request fail : " + response.statusCode());

        return mapper.readValue(response.body(), new TypeReference<>() {
        });
    }

    public TeslaGraphQLResponse<ChargingWrapper> getChargingSitePricingTesla(TeslaGraphQLRequest<ChargingSitePricing> chargingSitePricingRequest) throws Exception {

        String queryString = chargingSitePricingRequest.variables().toQueryString();
        String requestBodyString = mapper.writeValueAsString(chargingSitePricingRequest);

        HttpRequest request = requestBuilder
            .uri(URI.create(BASE_URL + "?" + queryString))
            .POST(HttpRequest.BodyPublishers.ofString(requestBodyString))
            .build();
        log.trace("request : {} / {}", request, requestBodyString);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.trace("response : {} / {}", response.statusCode(), response.body());

        if (response.statusCode() != 200)
            throw new Exception("http request fail : " + response.statusCode());

        return mapper.readValue(response.body(), new TypeReference<>() {
        });
    }
}
