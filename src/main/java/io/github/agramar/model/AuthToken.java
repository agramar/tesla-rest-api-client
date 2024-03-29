package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthToken(
    @JsonProperty("access_token")
    String accessToken,
    @JsonProperty("refresh_token")
    String refreshToken,
    @JsonProperty("expires_in")
    Integer expiresIn,
    @JsonProperty("id_token")
    String idToken,
    @JsonProperty("state")
    String state,
    @JsonProperty("token_type")
    String tokenType
) {
}
