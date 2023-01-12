package io.github.agramar.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.agramar.model.Extensions;

public record TeslaGraphQLResponse<T>(
    @JsonProperty("data")
    T data,
    @JsonProperty("extensions")
    Extensions extensions
) {
}
