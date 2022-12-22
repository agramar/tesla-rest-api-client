package io.github.agramar;

import io.github.agramar.domain.TeslaAuthToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Slf4j
public class TeslaRestApiAuth {

    final String USER_AGENT = "";

    /**
     * TODO 테슬라 인증 보안 때문에 해당 로직이 막혀서 잠정 중단...
     */
    public TeslaAuthToken getAccessToken(String email, String password) throws Exception {

        HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NEVER)
            .build();

        // 1. Obtain the login page
        final String codeVerifier = RandomStringUtils.randomAlphabetic(86);
        final String codeChallenge = Base64.getEncoder().encodeToString(DigestUtils.sha256(codeVerifier));
        final String state = RandomStringUtils.randomAlphabetic(20);
        log.trace("codeVerifier : {}", codeVerifier);
        log.trace("codeChallenge : {}", codeChallenge);
        log.trace("state : {}", state);

        HttpRequest loginPageRequest = HttpRequest.newBuilder()
            .header("User-Agent", USER_AGENT)
            .uri(URI.create("https://auth.tesla.com/oauth2/v3/authorize?" +
                "client_id=ownerapi" +
                "&code_challenge=" + codeChallenge +
                "&code_challenge_method=S256" +
                "&redirect_uri=https://auth.tesla.com/void/callback" +
                "&response_type=code" +
                "&scope=openid%20email%20offline_access" +
                "&state=" + state +
                "&login_hint=" + email
            ))
            .GET()
            .build();
        log.trace("authCodeRequest : {}", loginPageRequest);

        HttpResponse<String> loginPageResponse = httpClient.send(loginPageRequest, HttpResponse.BodyHandlers.ofString());
        log.trace("loginPageResponse : {}", loginPageResponse);

        // parse teslaAuthSid from header
        String teslaAuthSid = loginPageResponse.headers()
            .allValues("Set-Cookie")
            .stream()
            .filter(c -> c.contains("tesla-auth.sid"))
            .findFirst()
            .map(c -> c.split(";")[0])
            .orElseThrow(() -> new IllegalArgumentException("Set-Cookie value is empty"));
        log.trace("teslaAuthSid : {}", teslaAuthSid);

        // parse hidden form data
        List<String> formDataList = new ArrayList<>(Jsoup.parse(loginPageResponse.body())
            .select("input[type=hidden]")
            .stream()
            .map(hiddenInput -> {
                String name = hiddenInput.attr("name");
                String value = hiddenInput.attr("value");
                return name + "=" + value;
            })
            .toList());
        formDataList.add("identity=" + email);
        formDataList.add("credential=" + password);
        String formData = String.join("&", formDataList);
        log.trace("formData : {}", formData);

        // 2. Obtain authorization code
        HttpRequest authCodeRequest = HttpRequest.newBuilder()
            .header("User-Agent", USER_AGENT)
            .header("Cookie", teslaAuthSid)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .uri(URI.create("https://auth.tesla.com/oauth2/v3/authorize?" +
                "client_id=ownerapi" +
                "&code_challenge=" + codeChallenge +
                "&code_challenge_method=S256" +
                "&redirect_uri=https://auth.tesla.com/void/callback" +
                "&response_type=code" +
                "&scope=openid%20email%20offline_access" +
                "&state=" + state +
                "&login_hint=" + email
            ))
            .POST(HttpRequest.BodyPublishers.ofString(formData))
            .build();
        log.trace("authCodeRequest : {}", authCodeRequest);
        log.trace("authCodeRequest headers : {}", authCodeRequest.headers());

        HttpResponse<String> authCodeResponse = httpClient.send(authCodeRequest, HttpResponse.BodyHandlers.ofString());
        log.trace("authCodeResponse : {}", authCodeResponse);
        log.trace("authCodeResponse headers : {}", authCodeResponse.headers());
        log.trace("authCodeResponse body : {}", authCodeResponse.body());
        if (authCodeResponse.statusCode() != 302) {
            throw new Exception();
        }

        // TODO 3. Exchange authorization code for bearer token
        return TeslaAuthToken.builder()
            .accessToken("eyJaccess")
            .refreshToken("eyJrefresh")
            .expiresIn(300)
            .build();
    }

    public TeslaAuthToken refreshAccessToken() {
        // TODO 4. Refreshing an access token
        return TeslaAuthToken.builder()
            .accessToken("eyJaccess")
            .refreshToken("eyJrefresh")
            .expiresIn(300)
            .build();
    }
}
