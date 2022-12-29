package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeslaUserProfile(
    @JsonProperty("email") String email,
    @JsonProperty("full_name") String fullName,
    @JsonProperty("profile_image_url") String profileImageUrl
) {
}
