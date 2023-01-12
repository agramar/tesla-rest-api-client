package io.github.agramar.model.request;

import lombok.Builder;

@Builder
public record NearbyChargingSitesArgs(
    NearbyChargingSites args
) {
    public NearbyChargingSitesArgs {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
    }
}
