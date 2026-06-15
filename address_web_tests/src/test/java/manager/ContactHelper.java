package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    private void fillContactForm(ContactData contact) {
        waitForElement(By.name("firstname"), 5);

        if (contact.firstname() != null) {
            type(By.name("firstname"), contact.firstname());
        }
        if (contact.lastname() != null) {
            type(By.name("lastname"), contact.lastname());
        }
        if (contact.nickname() != null) {
            type(By.name("nickname"), contact.nickname());
        }
        if (contact.photo() != null) {
            selectAttach(By.name("photo"), contact.photo());
        }
        if (contact.email() != null) {
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
        manager.driver.findElements(By.name("selected[]")).forEach(WebElement::click);
    }

    private void removedSubmit() {
        click(By.name("remove"));
    }

    private void findContact(ContactData contact) {
        WebElement contactCheckbox = manager.driver.findElement(By.cssSelector(String.format("input[value='%s']", contact.id())));
        contactCheckbox.click();
    }

    private void selectGroupForRemoved(GroupData group) {
        Select groupDropdown = new Select(manager.driver.findElement(By.name("group")));
        groupDropdown.selectByValue(group.id());
        List<WebElement> contactsInGroup = manager.driver.findElements(By.name("selected[]"));
        if (contactsInGroup.isEmpty()) {
            throw new IllegalStateException("Группа не содержит контактов. Удаление невозможно.");
        }
    }

    public void clickSelectAll() {
        click(By.xpath("//*[@id=\"MassCB\"]"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }


    private void selectDropdown(By locator, String value) {
        WebElement dropdown = manager.driver.findElement(locator);
        dropdown.findElement(By.xpath(String.format("//option[. = '%s']", value))).click();
    }

    public void waitForElement(By locator, int timeoutInSeconds) {
        new WebDriverWait(manager.driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void editContact(int index) {
        click(By.cssSelector("img[title='Edit']"));
    }

    private void submitModifyContact() {
        click(By.name("update"));
    }

    private void selectAttach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    private void addTo() {
        click(By.name("add"));
    }

    private void contactNoneGroup(ContactData contact) {
        manager.driver.findElement(By.name("group")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("group"));
            dropdown.findElement(By.xpath("//option[. = '[none]']")).click();
        }
        manager.driver.findElement(By.xpath("//option[@value=\'[none]\']")).click();

    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public List<ContactData> getListContacts() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var row : rows) {
            var firstname = row.findElement(By.xpath("./td[3]")).getText();
            var lastname = row.findElement(By.xpath("./td[2]")).getText();
            var email = row.findElement(By.xpath("./td[5]")).getText();
            ;
            contacts.add(new ContactData()
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withEmail(email));
        }
        return contacts;
    }

    public void removeContact(ContactData contact) {
        openContactsPage();
        selectContact();
        clickDelete();
        try {
            var alert = manager.driver.switchTo().alert();
            alert.accept();
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            System.out.println("No alert present after click delete");
        }
        returnToHomePage();
    }

    public void removeAllContacts() {
        openContactsPage();
        clickSelectAll();
        clickDelete();
        try {
            var alert = manager.driver.switchTo().alert();
            alert.accept();
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            System.out.println("No alert present after click delete");
        }
        returnToHomePage();
    }

    public void createContact(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void modifyWithPhotoContact(int index, ContactData modifyData) {
        openContactsPage();
        click(By.xpath(String.format("//tr[@name='entry'][%d]/td[8]/a/img", index + 1)));
        fillContactForm(modifyData);
        submitModifyContact();
        returnToHomePage();

    }

    public void modifyContact(int index, ContactData modifyData) {
        openContactsPage();
        editContact(index);
        fillContactForm(modifyData);
        submitModifyContact();
        returnToHomePage();

    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createB(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void addIntoGroup(ContactData contact, GroupData group) {
        openContactsPage();
        contactNoneGroup(contact);
        findContact(contact);
        addTo();
    }

    public void removed(ContactData contact, GroupData group) {
        openContactsPage();
        selectGroupForRemoved(group);
        findContact(contact);
        removedSubmit();
        openContactsPage();
    }

    public Object getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }
    public Object getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public Map<String, String>getPhones(){
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }
    public Map<String, String>getEmails(){
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }
}
