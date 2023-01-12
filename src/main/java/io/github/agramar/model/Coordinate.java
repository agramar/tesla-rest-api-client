package io.github.agramar.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Coordinate(
    BigDecimal latitude,
    BigDecimal longitude
) {
}
