package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record Vehicle(
    @JsonProperty("id")
    Long id,
    @JsonProperty("vehicle_id")
    Long vehicleId,
    @JsonProperty("vin")
    String vin,
    @JsonProperty("display_name")
    String displayName,
    @JsonProperty("option_codes")
    String optionCodes,
    @JsonProperty("access_type")
    String accessType,
    @JsonProperty("color")
    String color,
    @JsonProperty("tokens")
    List<String> tokens,
    @JsonProperty("state")
    String state,
    @JsonProperty("in_service")
    Boolean inService,
    @JsonProperty("id_s")
    String idS,
    @JsonProperty("calendar_enabled")
    Boolean calendarEnabled,
    @JsonProperty("api_version")
    Integer apiVersion,
    @JsonProperty("backseat_token")
    String backseatToken,
    @JsonProperty("backseat_token_updated_at")
    String backseatTokenUpdatedAt,
    @JsonProperty("charge_state")
    ChargeState chargeState,
    @JsonProperty("climate_state")
    ClimateState climateState,
    @JsonProperty("drive_state")
    DriveState driveState,
    @JsonProperty("gui_settings")
    GuiSettings guiSettings,
    @JsonProperty("vehicle_config")
    VehicleConfig vehicleConfig,
    @JsonProperty("vehicle_state")
    VehicleState vehicleState
) {
}
