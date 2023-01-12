package io.github.agramar;

import io.github.agramar.model.*;
import io.github.agramar.model.request.ChargingSitePricing;
import io.github.agramar.model.request.NearbyChargingSites;
import io.github.agramar.model.request.NearbyChargingSitesArgs;
import io.github.agramar.model.request.TeslaGraphQLRequest;
import io.github.agramar.model.response.TeslaGraphQLResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.github.agramar.TestArgument.ACCESS_TOKEN;
import static org.junit.jupiter.api.Assertions.*;

class TeslaGraphQLApiTest {

    static String vin;

    @BeforeAll
    static void beforeAll() throws Exception {
        TeslaVehiclesApi teslaVehiclesApi = new TeslaVehiclesApi(ACCESS_TOKEN.getValue());
        List<Vehicle> vehicles = teslaVehiclesApi.getVehicles().response();
        vin = vehicles.get(vehicles.size() - 1).vin();
    }

    TeslaGraphQLApi teslaGraphQLApi;

    @BeforeEach
    void setUp() {
        teslaGraphQLApi = new TeslaGraphQLApi(ACCESS_TOKEN.getValue());
    }

    @Test
    void testGetNearbyChargingSites() throws Exception {

        TeslaGraphQLRequest<NearbyChargingSitesArgs> request = TeslaGraphQLRequest.<NearbyChargingSitesArgs>builder()
            .query(GraphQLOperations.GET_NEARBY_CHARGING_SITES.getQuery())
            .operationName(GraphQLOperations.GET_NEARBY_CHARGING_SITES.getName())
            .variables(NearbyChargingSitesArgs.builder()
                .args(NearbyChargingSites.builder()
                    .userLocation(Coordinate.builder().latitude(new BigDecimal("37.6099897")).longitude(new BigDecimal("127.0742467")).build())
                    .countryCode(TestArgument.COUNTRY_CODE.getValue())
                    .languageCode(TestArgument.LANGUAGE_CODE.getValue())
                    .vin(vin)
                    .build())
                .build())
            .build();

        TeslaGraphQLResponse<ChargingWrapper> response = teslaGraphQLApi.getNearbyChargingSites(request);
        assertNotNull(response);

        ChargingWrapper chargingWrapper = response.data();
        assertNotNull(chargingWrapper);

        Charging charging = chargingWrapper.charging();
        assertNotNull(charging);

        NearbySites nearbySites = charging.nearbySites();
        assertNotNull(nearbySites);

        List<SitesAndDistance> sitesAndDistances = nearbySites.sitesAndDistances();
        assertNotNull(sitesAndDistances);
        assertTrue(sitesAndDistances.size() > 0);
    }

    @Test
    void testGetChargingSitePricingTesla() throws Exception {

        TeslaGraphQLRequest<ChargingSitePricing> request = TeslaGraphQLRequest.<ChargingSitePricing>builder()
            .query(GraphQLOperations.GET_CHARGING_SITE_PRICING_TESLA.getQuery())
            .operationName(GraphQLOperations.GET_CHARGING_SITE_PRICING_TESLA.getName())
            .variables(ChargingSitePricing.builder()
                .siteId(TestArgument.SITE_ID.getValue())
                .ttpLocale(TestArgument.TTP_LOCALE.getValue())
                .deviceLanguage(TestArgument.LANGUAGE_CODE.getValue())
                .deviceCountry(TestArgument.COUNTRY_CODE.getValue())
                .upselling(true)
                .build())
            .build();

        TeslaGraphQLResponse<ChargingWrapper> response = teslaGraphQLApi.getChargingSitePricingTesla(request);
        assertNotNull(response);

        ChargingWrapper chargingWrapper = response.data();
        assertNotNull(chargingWrapper);

        Charging charging = chargingWrapper.charging();
        assertNotNull(charging);

        SitePricing sitePricing = charging.sitePricing();
        assertNotNull(sitePricing);

        Rates userRates = sitePricing.userRates();
        assertNotNull(userRates);

        Rates memberRates = sitePricing.memberRates();
        assertEquals(request.variables().upselling(), memberRates != null);
    }
}
