package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserVaultProfile(
    @JsonProperty("vault")
    String vault
) {
}
