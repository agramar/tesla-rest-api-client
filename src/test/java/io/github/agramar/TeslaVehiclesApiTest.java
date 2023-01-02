package io.github.agramar;

import io.github.agramar.model.TeslaResponse;
import io.github.agramar.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.agramar.TestArgument.ACCESS_TOKEN;
import static org.junit.jupiter.api.Assertions.*;

class TeslaVehiclesApiTest {

    TeslaVehiclesApi teslaVehiclesApi;

    @BeforeEach
    void setUp() {
        teslaVehiclesApi = new TeslaVehiclesApi(ACCESS_TOKEN.getValue());
    }

    @Test
    void testVehiclesApi() throws Exception {

        TeslaResponse<List<Vehicle>> vehicles = teslaVehiclesApi.getVehicles();

        assertTrue(vehicles.count() != 0);
        assertNotNull(vehicles.response());

        for (Vehicle vehicle : vehicles.response()) {
            TeslaResponse<Vehicle> aVehicle = teslaVehiclesApi.getVehicle(vehicle.id());

            assertNotNull(aVehicle.response());
            assertEquals(vehicle.id(), aVehicle.response().id());
        }
    }
}
