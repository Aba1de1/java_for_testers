package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class AddContactTest extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData()
                .withFirstname("First")
                .withLastname("Mid")
                .withNickname("Last")
                .withEmail("Email@email.com")
                .withBday("1")
                .withBmonth("January")
                .withByear("2000"));
    }

    @Test
    public void createContactWithOnlyName() {
        app.contacts().createContact(new ContactData()
                .withFirstname("Second")
                .withLastname("Lastone")
                .withNickname("")
                .withEmail("")
                .withBday("")
                .withBmonth("")
                .withByear(""));
    }
}