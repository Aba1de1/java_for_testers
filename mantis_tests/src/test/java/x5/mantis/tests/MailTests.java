package x5.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import x5.mantis.model.MailMassage;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MailTests extends TestBase {

    @Test
    void canDrainInbox(){
        app.mail().drain("hly@localhost", "password");
    }

    @Test
    void canReceiveEmail() {
        var messages = app.mail().receive("hly@localhost", "password", Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }


    @Test
    void canExtractUrl() {
        List<MailMassage> messages;
        messages = app.mail().receive("qwe@localhost", "password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }
}
