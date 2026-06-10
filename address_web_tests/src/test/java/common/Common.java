package common;

import tests.TestBase;

import java.util.Random;

public class Common {
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++){
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }

    public static String randomEmail() {
        return TestBase.randomContact(8) + "@example.com";
    }
}
