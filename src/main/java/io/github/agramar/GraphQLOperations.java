package io.github.agramar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GraphQLOperations {
    GET_NEARBY_CHARGING_SITES("GetNearbyChargingSites", "\n    query GetNearbyChargingSites($args: GetNearbyChargingSitesRequestType!) {\n  charging {\n    nearbySites(args: $args) {\n      sitesAndDistances {\n        ...ChargingNearbySitesFragment\n      }\n    }\n  }\n}\n    \n    fragment ChargingNearbySitesFragment on ChargerSiteAndDistanceType {\n  activeOutages {\n    message\n  }\n  availableStalls {\n    value\n  }\n  centroid {\n    ...EnergySvcCoordinateTypeFields\n  }\n  drivingDistanceMiles {\n    value\n  }\n  entryPoint {\n    ...EnergySvcCoordinateTypeFields\n  }\n  haversineDistanceMiles {\n    value\n  }\n  id {\n    text\n  }\n  localizedSiteName {\n    value\n  }\n  maxPowerKw {\n    value\n  }\n  totalStalls {\n    value\n  }\n  siteType\n  accessType\n}\n    \n    fragment EnergySvcCoordinateTypeFields on EnergySvcCoordinateType {\n  latitude\n  longitude\n}\n    "),
    GET_CHARGING_SITE_PRICING_TESLA("GetChargingSitePricingTsla", "\n    query GetChargingSitePricing($siteId: String!, $deviceCountry: String!, $deviceLanguage: String!, $upselling: Boolean) {\n  charging {\n    sitePricing(\n      siteId: $siteId\n      deviceCountry: $deviceCountry\n      deviceLanguage: $deviceLanguage\n      upselling: $upselling\n    ) {\n      userRates {\n        ...ChargingSiteRatesFragment\n      }\n      memberRates {\n        ...ChargingSiteRatesFragment\n      }\n    }\n  }\n}\n    \n    fragment ChargingSiteRatesFragment on ChargingSiteRatesType {\n  charging {\n    uom\n    rates\n    currencyCode\n    programType\n    priceBookID\n    vehicleMakeType\n  }\n  parking {\n    uom\n    rates\n    currencyCode\n    programType\n    priceBookID\n    vehicleMakeType\n  }\n}\n    "),
    ;

    private final String name;
    private final String query;
}
