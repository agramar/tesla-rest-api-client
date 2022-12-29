package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeslaResponse<T>(
    @JsonProperty("response") T response
) {
}
