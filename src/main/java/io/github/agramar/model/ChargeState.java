package io.github.agramar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

public record ChargeState(
    @JsonProperty("battery_heater_on")
    Boolean batteryHeaterOn,
    @JsonProperty("battery_level")
    Integer batteryLevel,
    @JsonProperty("battery_range")
    BigDecimal batteryRange,
    @JsonProperty("charge_amps")
    Integer chargeAmps,
    @JsonProperty("charge_current_request")
    Integer chargeCurrentRequest,
    @JsonProperty("charge_current_request_max")
    Integer chargeCurrentRequestMax,
    @JsonProperty("charge_enable_request")
    Boolean chargeEnableRequest,
    @JsonProperty("charge_energy_added")
    BigDecimal chargeEnergyAdded,
    @JsonProperty("charge_limit_soc")
    Integer chargeLimitSoc,
    @JsonProperty("charge_limit_soc_max")
    BigDecimal chargeLimitSocMax,
    @JsonProperty("charge_limit_soc_min")
    Integer chargeLimitSocMin,
    @JsonProperty("charge_limit_soc_std")
    Integer chargeLimitSocStd,
    @JsonProperty("charge_miles_added_ideal")
    BigDecimal chargeMilesAddedIdeal,
    @JsonProperty("charge_miles_added_rated")
    BigDecimal chargeMilesAddedRated,
    @JsonProperty("charge_port_cold_weather_mode")
    Boolean chargePortColdWeatherMode,
    @JsonProperty("charge_port_color")
    String chargePortColor,
    @JsonProperty("charge_port_door_open")
    Boolean chargePortDoorOpen,
    @JsonProperty("charge_port_latch")
    String chargePortLatch,
    @JsonProperty("charge_rate")
    BigDecimal chargeRate,
    @JsonProperty("charger_actual_current")
    Integer chargerActualCurrent,
    @JsonProperty("charger_phases")
    Integer chargerPhases,
    @JsonProperty("charger_pilot_current")
    Integer chargerPilotCurrent,
    @JsonProperty("charger_power")
    Integer chargerPower,
    @JsonProperty("charger_voltage")
    Integer chargerVoltage,
    @JsonProperty("charging_state")
    String chargingState,
    @JsonProperty("conn_charge_cable")
    String connChargeCable,
    @JsonProperty("est_battery_range")
    BigDecimal estBatteryRange,
    @JsonProperty("fast_charger_brand")
    String fastChargerBrand,
    @JsonProperty("fast_charger_present")
    Boolean fastChargerPresent,
    @JsonProperty("fast_charger_type")
    String fastChargerType,
    @JsonProperty("ideal_battery_range")
    BigDecimal idealBatteryRange,
    @JsonProperty("managed_charging_active")
    Boolean managedChargingActive,
    @JsonProperty("managed_charging_start_time")
    Long managedChargingStartTime,
    @JsonProperty("managed_charging_user_canceled")
    Boolean managedChargingUserCanceled,
    @JsonProperty("max_range_charge_counter")
    Integer maxRangeChargeCounter,
    @JsonProperty("minutes_to_full_charge")
    Integer minutesToFullCharge,
    @JsonProperty("not_enough_power_to_heat")
    String notEnoughPowerToHeat,
    @JsonProperty("off_peak_charging_enabled")
    Boolean offPeakChargingEnabled,
    @JsonProperty("off_peak_charging_times")
    String offPeakChargingTimes,
    @JsonProperty("off_peak_hours_end_time")
    Integer offPeakHoursEndTime,
    @JsonProperty("preconditioning_enabled")
    Boolean preconditioningEnabled,
    @JsonProperty("preconditioning_times")
    String preconditioningTimes,
    @JsonProperty("scheduled_charging_mode")
    String scheduledChargingMode,
    @JsonProperty("scheduled_charging_pending")
    Boolean scheduledChargingPending,
    @JsonProperty("scheduled_charging_start_time")
    Long scheduledChargingStartTime,
    @JsonProperty("scheduled_charging_start_time_app")
    Integer scheduledChargingStartTimeApp,
    @JsonProperty("scheduled_departure_time")
    Integer scheduledDepartureTime,
    @JsonProperty("supercharger_session_trip_planner")
    Boolean superchargerSessionTripPlanner,
    @JsonProperty("time_to_full_charge")
    Long timeToFullCharge,
    @JsonProperty("timestamp")
    Instant timestamp,
    @JsonProperty("trip_charging")
    Boolean tripCharging,
    @JsonProperty("usable_battery_level")
    Integer usableBatteryLevel,
    @JsonProperty("user_charge_enable_request")
    Boolean userChargeEnableRequest
) {
}
