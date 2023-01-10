package io.github.agramar.util;

import java.net.http.HttpResponse;

public class HttpUtils {
    public final static HttpResponse.BodyHandler<String> STRING_RESPONSE_HANDLER = HttpResponse.BodyHandlers.ofString();
}
