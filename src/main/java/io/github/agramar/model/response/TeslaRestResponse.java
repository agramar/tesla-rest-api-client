package io.github.agramar.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeslaRestResponse<T>(
    @JsonProperty("response")
    T response,
    @JsonProperty("count")
    Integer count
) {
}
