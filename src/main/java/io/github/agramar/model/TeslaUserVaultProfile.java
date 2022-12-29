package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeslaUserVaultProfile(
    @JsonProperty("vault") String vault
) {
}
