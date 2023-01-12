package io.github.agramar;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agramar.model.AuthToken;
import io.github.agramar.model.request.RefreshTokenRequest;
import io.github.agramar.util.MediaType;
import io.github.agramar.util.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;


@Slf4j
public class TeslaAuthApi {

    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public TeslaAuthApi() {
        this.httpClient = HttpClient.newBuilder().build();
        this.mapper = ObjectMapperHolder.INSTANCE.get();
    }

    /**
     * TODO Auth API Not working
     */
    public AuthToken getAccessToken(final String email, final String password) {
        log.trace("email : {}", email);
        log.trace("password : {}", password);

        // TODO 1. Obtain the login page
        final String codeVerifier = RandomStringUtils.randomAlphabetic(86);
        final String codeChallenge = Base64.getEncoder().encodeToString(DigestUtils.sha256(codeVerifier));
        final String state = RandomStringUtils.randomAlphabetic(20);
        log.trace("codeVerifier : {}", codeVerifier);
        log.trace("codeChallenge : {}", codeChallenge);
        log.trace("state : {}", state);

        // TODO 2. Obtain authorization code
        // TODO 3. Exchange authorization code for bearer token
        return new AuthToken("eyJaccess", "eyJrefresh", 300, "", "", "");
    }

    public AuthToken refreshAccessToken(RefreshTokenRequest refreshTokenRequest) throws Exception {

        final String BASE_URL = "https://auth.tesla.com/oauth2/v3";

        if (refreshTokenRequest == null)
            throw new IllegalArgumentException();

        String formUrlEncoded = refreshTokenRequest.toFormUrlEncoded();
        log.trace("formUrlEncoded : {}", formUrlEncoded);

        HttpRequest request = HttpRequest.newBuilder()
            .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.getValue())
            .uri(URI.create(BASE_URL + "/token"))
            .POST(HttpRequest.BodyPublishers.ofString(formUrlEncoded))
            .build();
        log.trace("request : {}", request);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.trace("response : {}", response);

        if (response.statusCode() != 200) {
            log.error("http request fail : {} {}", response.statusCode(), response.body());
            throw new Exception("http request fail : " + response.statusCode());
        }

        String responseBody = response.body();
        log.trace("response body : {}", responseBody);

        return mapper.readValue(responseBody, AuthToken.class);
    }
}
