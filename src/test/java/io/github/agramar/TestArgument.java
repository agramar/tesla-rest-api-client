package io.github.agramar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestArgument {
    // TODO 프로퍼티에서 읽어오게
    EMAIL("email"),
    PASSWORD("password"),
    ACCESS_TOKEN(""),
    REFRESH_TOKEN(""),
    COUNTRY_CODE("KR"),
    LANGUAGE_CODE("ko"),
    TTP_LOCALE("ko_KR"),
    SITE_ID("d50d4562-e2f2-4789-9338-aeaf591f2571"),
    ;

    private final String value;
}
