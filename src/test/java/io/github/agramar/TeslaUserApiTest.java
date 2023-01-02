package io.github.agramar;

import io.github.agramar.model.TeslaResponse;
import io.github.agramar.model.UserProfile;
import io.github.agramar.model.UserVaultProfile;
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

        TeslaResponse<UserProfile> response = teslaUserApi.getMe();

        assertNotNull(response);
        assertNotNull(response.response());
        assertNotNull(response.response().fullName());
        assertNotNull(response.response().email());
        assertNotNull(response.response().profileImageUrl());
    }

    @Test
    void testGetVaultProfile() throws Exception {

        TeslaResponse<UserVaultProfile> response = teslaUserApi.getVaultProfile();

        assertNotNull(response);
        assertNotNull(response.response());
        assertNotNull(response.response().vault());
    }
}
