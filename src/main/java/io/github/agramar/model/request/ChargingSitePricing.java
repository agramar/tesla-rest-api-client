package io.github.agramar.model.request;

import lombok.Builder;

@Builder
public record ChargingSitePricing(
    String siteId,
    String ttpLocale,
    String deviceLanguage,
    String deviceCountry,
    Boolean upselling
) {
    public ChargingSitePricing {
        if (siteId == null || siteId.isBlank())
            throw new IllegalArgumentException("siteId can not be blank.");

        if (ttpLocale == null || ttpLocale.isBlank())
            throw new IllegalArgumentException("ttpLocale can not be blank.");

        if (deviceLanguage == null || deviceLanguage.isBlank())
            throw new IllegalArgumentException("deviceLanguage can not be blank.");

        if (deviceCountry == null || deviceCountry.isBlank())
            throw new IllegalArgumentException("deviceCountry can not be blank.");

        if (upselling == null)
            upselling = true;
    }

    public String toQueryString() {
        return String.join("&",
            "deviceLanguage=" + deviceLanguage,
            "deviceCountry=" + deviceCountry,
            "ttpLocale=" + ttpLocale,
            "operationName=GetChargingSitePricing"
        );
    }
}
