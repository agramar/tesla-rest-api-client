package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserProfile(
    @JsonProperty("email")
    String email,
    @JsonProperty("full_name")
    String fullName,
    @JsonProperty("profile_image_url")
    String profileImageUrl
) {
}
