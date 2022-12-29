package io.github.agramar.model;

import lombok.Builder;

@Builder
public record RefreshTokenRequest(
    String grantType,
    String clientId,

    String refreshToken,
    String scope
) {
    public RefreshTokenRequest {
        if (refreshToken == null || refreshToken.isBlank())
            throw new IllegalArgumentException();

        if (grantType == null || grantType.isBlank())
            grantType = "refresh_token";

        if (clientId == null || clientId.isBlank())
            clientId = "ownerapi";

        if (scope == null || scope.isBlank())
            scope = "openid email offline_access";
    }

    public String toFormUrlEncoded() {
        return String.join("&",
            "grant_type=" + grantType,
            "client_id=" + clientId,
            "refresh_token=" + refreshToken,
            "scope=" + scope
        );
    }
}
