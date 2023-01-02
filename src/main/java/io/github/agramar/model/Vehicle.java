package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Vehicle(
    @JsonProperty("id") long id,
    @JsonProperty("vehicle_id") long vehicleId,
    @JsonProperty("vin") String vin,
    @JsonProperty("display_name") String displayName,
    @JsonProperty("option_codes") String optionCodes,
    @JsonProperty("access_type") String accessType,
    @JsonProperty("color") String color,
    @JsonProperty("tokens") List<String> tokens,
    @JsonProperty("state") String state,
    @JsonProperty("in_service") boolean inService,
    @JsonProperty("id_s") String idS,
    @JsonProperty("calendar_enabled") boolean calendarEnabled,
    @JsonProperty("api_version") int apiVersion,
    @JsonProperty("backseat_token") String backseatToken,
    @JsonProperty("backseat_token_updated_at") String backseatTokenUpdatedAt
) {

}
