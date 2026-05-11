package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class RemoveContactTest extends TestBase {

    @Test
    public void canCreateContact() {
        if(app.contacts().isContactPresent()){
        app.contacts().createContact(new ContactData()
                .withFirstname("Dont")
                .withLastname("Delete")
                .withNickname("Me")
                .withEmail("Please@email.com")
                .withBday("7")
                .withBmonth("December")
                .withByear("2007"));
        }
        app.contacts().removeContact();
    }
}