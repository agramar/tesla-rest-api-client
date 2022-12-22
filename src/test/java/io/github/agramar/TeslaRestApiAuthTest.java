package io.github.agramar;

import io.github.agramar.domain.TeslaAuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeslaRestApiAuthTest {

    TeslaRestApiAuth teslaRestApiAuth;

    @BeforeEach
    void setUp() {
        teslaRestApiAuth = new TeslaRestApiAuth();
    }

    @Test
    void testGenAccessToken() throws Exception {
        String email = "";
        String password = "";

        TeslaAuthToken token = teslaRestApiAuth.getAccessToken(email, password);

        Assertions.assertNotNull(token.getAccessToken());
        Assertions.assertNotNull(token.getRefreshToken());
    }
}
