package io.github.agramar.model;

import java.math.BigDecimal;
import java.util.List;

public record Rate(
    String uom,
    List<BigDecimal> rates,
    String currencyCode,
    String programType,
    Long priceBookID,
    String vehicleMakeType
) {
}
