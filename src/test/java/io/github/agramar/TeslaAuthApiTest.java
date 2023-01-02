package io.github.agramar;

import io.github.agramar.model.RefreshTokenRequest;
import io.github.agramar.model.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.github.agramar.TestArgument.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class TeslaAuthApiTest {

    TeslaAuthApi teslaAuthApi;

    @BeforeEach
    void setUp() {
        teslaAuthApi = new TeslaAuthApi();
    }

    @Test
    void testGenAccessToken() {

        AuthToken token = teslaAuthApi.getAccessToken(EMAIL.getValue(), PASSWORD.getValue());

        log.trace("token : {}", token);

        assertNotNull(token.accessToken());
        assertNotNull(token.refreshToken());
    }

    @Test
    void testRefreshAccessToken() throws Exception {
        RefreshTokenRequest refreshTokenRequest = RefreshTokenRequest.builder()
            .refreshToken(REFRESH_TOKEN.getValue())
            .build();

        AuthToken token = teslaAuthApi.refreshAccessToken(refreshTokenRequest);

        log.trace("token : {}", token);

        assertNotNull(token);
        assertNotNull(token.accessToken());
        assertNotNull(token.refreshToken());
    }
}
