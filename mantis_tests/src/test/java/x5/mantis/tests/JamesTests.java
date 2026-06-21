package x5.mantis.tests;

import org.junit.jupiter.api.Test;
import x5.mantis.common.Common;

public class JamesTests extends TestBase{

    @Test
    void canCreateduser(){
        app.jamesCli().addUser(String.format("%s@localhost", Common.randomString(8)),"password");
    }
}
