package io.github.agramar;

import io.github.agramar.model.TeslaResponse;
import io.github.agramar.model.TeslaUserProfile;
import io.github.agramar.model.TeslaUserVaultProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.github.agramar.TestArgument.ACCESS_TOKEN;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TeslaUserApiTest {

    TeslaUserApi teslaUserApi;

    @BeforeEach
    void setUp() {
        teslaUserApi = new TeslaUserApi(ACCESS_TOKEN.getValue());
    }

    @Test
    void testGetMe() throws Exception {

        TeslaResponse<TeslaUserProfile> response = teslaUserApi.getMe();

        assertNotNull(response);
        assertNotNull(response.response());
        assertNotNull(response.response().fullName());
        assertNotNull(response.response().email());
        assertNotNull(response.response().profileImageUrl());
    }

    @Test
    void testGetVaultProfile() throws Exception {

        TeslaResponse<TeslaUserVaultProfile> response = teslaUserApi.getVaultProfile();

        assertNotNull(response);
        assertNotNull(response.response());
        assertNotNull(response.response().vault());
    }
}
