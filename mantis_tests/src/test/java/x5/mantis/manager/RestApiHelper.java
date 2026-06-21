package x5.mantis.manager;


import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import okhttp3.*;
import x5.mantis.model.IssueData;

import java.io.IOException;

public class RestApiHelper extends HelperBase {
    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client;

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey("apiKey");
        client = new OkHttpClient();
    }

    public void createIssue(IssueData issueData) {

        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());

        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);

        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerUser(String username, String email, String password) {
        String json = String.format(
                "{" +
                        "\"username\": \"%s\"," +
                        "\"password\": \"%s\"," +
                        "\"email\": \"%s\"" +
                        "}", username, password, email);

        RequestBody body = RequestBody.create(json, JSON);


        Request request = new Request.Builder()
                .url(manager.property("mantis.apiUrl") + "/users/")
                .post(body)
                .addHeader("Authorization", manager.property("apiKey"))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Ошибка при регистрации пользователя: " + response.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при выполнении запроса: " + e.getMessage(), e);
        }
    }
}
