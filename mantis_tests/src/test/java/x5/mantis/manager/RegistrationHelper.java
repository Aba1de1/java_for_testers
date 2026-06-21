package x5.mantis.manager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void start(String username, String email) {
        wd.get(manager.property("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
        proceed();
    }

    public void finish(String confirmationUrl, String password) {
        wd.get(confirmationUrl);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        clickUpdateUser();
    }

    public void proceed() {
        click(By.xpath("//a[contains(text(), 'Proceed')]"));
        ;
    }

    public void clickUpdateUser() {
        click(By.xpath("//*[@id='account-update-form']/fieldset/span/button"));
    }
}
