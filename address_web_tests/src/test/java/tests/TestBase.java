package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"));
        }
    }

    public static String randomContact(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt((int) (Math.random() * characters.length())));
        }
        return result.toString();
    }

    public static String randomEmail() {
        return randomContact(8) + "@example.com";
    }

    public static String randomPhoto (String dir) {
        var fileNames = new File(dir).list();
        var rnd = new Random();
        int index = 0;
        if (fileNames != null) {
            index = rnd.nextInt(fileNames.length);
        }
        return Paths.get(dir, fileNames[index]).toString();
    }
}
