package io.github.agramar.model;

import java.math.BigDecimal;
import java.util.List;

public record SitesAndDistance(
    List<?> activeOutages,      // 가동 중단 알림
    Value<Integer> availableStalls,
    Coordinate centroid,        // 중앙 좌표
    Coordinate entryPoint,      // 입구 좌표
    Value<BigDecimal> drivingDistanceMiles,     // 운전 거리
    Value<BigDecimal> haversineDistanceMiles,   // 직선 거리
    Text id,
    Value<String> localizedSiteName,
    Value<Integer> maxPowerKw,
    Value<Integer> totalStalls,
    String siteType,
    String accessType
) {
}
