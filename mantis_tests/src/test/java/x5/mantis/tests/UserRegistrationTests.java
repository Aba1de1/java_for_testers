package x5.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import x5.mantis.common.Common;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {
    @Test
    void canRegisterUser() {
        String username = Common.randomString(3);
        String email = username + "@localhost";
        String password = "password";
        app.jameCli().addUser(email, password);

        app.registration().start(username, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        Assertions.assertFalse(messages.isEmpty(), "Письмо не было получено");

        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        Assertions.assertTrue(matcher.find(), "Ссылка для подтверждения не найдена в письме");
        var confirmationUrl = text.substring(matcher.start(), matcher.end());

        String newPassword = "newpassword";
        app.registration().finish(confirmationUrl, newPassword);

        app.http().login(username, newPassword);
        boolean isLoggedIn = app.http().isLoggedIn();
        Assertions.assertTrue(isLoggedIn, "Не удалось войти с новым паролем");

    }
}
