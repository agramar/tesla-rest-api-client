package io.github.agramar;

import io.github.agramar.model.ChargeState;
import io.github.agramar.model.response.TeslaRestResponse;
import io.github.agramar.model.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.agramar.TestArgument.ACCESS_TOKEN;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TeslaVehiclesStateApiTest {

    static Long id;

    @BeforeAll
    static void beforeAll() throws Exception {
        TeslaVehiclesApi teslaVehiclesApi = new TeslaVehiclesApi(ACCESS_TOKEN.getValue());
        List<Vehicle> vehicles = teslaVehiclesApi.getVehicles().response();
        id = vehicles.get(vehicles.size() - 1).id();
    }

    TeslaVehiclesStateApi vehiclesStateApi;


    @BeforeEach
    void setUp() {
        vehiclesStateApi = new TeslaVehiclesStateApi(ACCESS_TOKEN.getValue());
    }

    @Test
    void testGetVehicleDate() throws Exception {
        TeslaRestResponse<Vehicle> response = vehiclesStateApi.getVehicleDate(id);

        Vehicle vehicle = response.response();
        assertNotNull(vehicle);

        ChargeState chargeState = vehicle.chargeState();
        assertNotNull(chargeState);
    }

    @Test
    void testGetChargeState() throws Exception {
        TeslaRestResponse<ChargeState> response = vehiclesStateApi.getChargeState(id);

        ChargeState chargeState = response.response();
        assertNotNull(chargeState);
    }

    @Test
    void testGetMobileEnabled() throws Exception {
        TeslaRestResponse<Boolean> response = vehiclesStateApi.getMobileEnabled(id);
        assertNotNull(response.response());
    }
}
