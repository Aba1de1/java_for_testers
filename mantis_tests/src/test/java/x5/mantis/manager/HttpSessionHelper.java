package x5.mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

import static io.swagger.client.auth.OAuthFlow.password;

public class HttpSessionHelper extends HelperBase {

    OkHttpClient client;

    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseUrl")))
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(manager.property("web.baseUrl"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            assert response.body() != null;
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loginApi(String username, String newPassword) {
        try {
            var client = new OkHttpClient();
            var request = new Request.Builder()
                    .url(manager.property("web.baseUrl") + "/login.php")
                    .post(RequestBody.create(
                            MediaType.parse("application/x-www-form-urlencoded"),
                            String.format("username=%s&password=%s", username, password)
                    ))
                    .build();

            try (var response = client.newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while logging in", e);
        }
    }
}
