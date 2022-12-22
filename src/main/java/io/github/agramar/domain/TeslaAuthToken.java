package io.github.agramar.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public record TeslaAuthToken(
    String accessToken,
    String refreshToken,
    int expiresIn,
    String idToken,
    String state,
    TokenType tokenType
) {
}
