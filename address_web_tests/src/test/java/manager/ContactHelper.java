package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    public boolean isContactPresent(){
        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact(){
        openContactsPage();
        selectContact();
        click(By.name("delete"));
        returnToHomePage();
    }

    public void createContact(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        type(By.name("email"), contact.email());

        if (contact.bday() != null) {
            selectDropdown(By.name("bday"), contact.bday());
        }
        if (contact.bmonth() != null) {
            selectDropdown(By.name("bmonth"), contact.bmonth());
        }
        if (contact.byear() != null) {
            type(By.name("byear"), contact.byear());
        }
    }

    private void selectDropdown(By locator, String value) {
        WebElement dropdown = manager.driver.findElement(locator);
        dropdown.findElement(By.xpath(String.format("//option[. = '%s']", value))).click();
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }
}