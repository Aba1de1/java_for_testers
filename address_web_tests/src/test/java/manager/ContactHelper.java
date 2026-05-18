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
    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

        private void clickDelete() {
        click(By.name("delete"));
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
    private void selectAllContact() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
    public void removeAllContacts(){
        selectAllContact();
        removeContact();
    }
    public void selectContact(){
        click(By.name("selected[]"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }
    public boolean isContactPresent(){
        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void selectDropdown(By locator, String value) {
        WebElement dropdown = manager.driver.findElement(locator);
        dropdown.findElement(By.xpath(String.format("//option[. = '%s']", value))).click();
}
    public void removeContact(){
        openContactsPage();
        selectContact();
        clickDelete();
        returnToHomePage();
    }

    public void createContact(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    private void fillContactForm(ContactData contact) {
        if (contact.firstname() != null) {
            type(By.name("firstname"), contact.firstname());
        }
        if (contact.lastname() != null) {
        type(By.name("lastname"), contact.lastname());
        }
        if (contact.nickname() != null) {
        type(By.name("nickname"), contact.nickname());
        }
        if (contact.bday() != null) {
        type(By.name("email"), contact.email());
        }
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

}