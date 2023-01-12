package io.github.agramar.model;

import java.util.List;

public record NearbySites(
    List<SitesAndDistance> sitesAndDistances
) {
}
