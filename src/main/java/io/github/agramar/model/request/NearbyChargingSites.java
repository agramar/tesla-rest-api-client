package io.github.agramar.model.request;

import io.github.agramar.model.Coordinate;
import io.github.agramar.model.Value;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record NearbyChargingSites(
    Coordinate userLocation,
    Coordinate northwestCorner,
    Coordinate southeastCorner,
    Value<Boolean> openToNonTeslasFilter,
    String languageCode,
    String countryCode,
    String vin
) {
    public NearbyChargingSites {
        if (vin == null || vin.isBlank())
            throw new IllegalArgumentException("vin can not be blank.");

        if (languageCode == null || languageCode.isBlank())
            throw new IllegalArgumentException("languageCode can not be blank.");

        if (countryCode == null || countryCode.isBlank())
            throw new IllegalArgumentException("countryCode can not be blank.");

        if (userLocation == null)
            throw new IllegalArgumentException("userLocation can not be null.");

        if (northwestCorner == null)
            northwestCorner = Coordinate.builder().latitude(new BigDecimal("90")).longitude(new BigDecimal("-180")).build();

        if (southeastCorner == null)
            southeastCorner = Coordinate.builder().latitude(new BigDecimal("-90")).longitude(new BigDecimal("180")).build();

        if (openToNonTeslasFilter == null)
            openToNonTeslasFilter = new Value<>(false);
    }
}
