package io.github.agramar.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded");

    private final String value;
}
