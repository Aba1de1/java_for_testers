package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            WebElement element = manager.driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }
}
