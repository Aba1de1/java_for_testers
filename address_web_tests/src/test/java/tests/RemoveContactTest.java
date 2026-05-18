package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveContactTest extends TestBase {

    @Test
    public void canCreateContact() {
        if(app.contacts().getCount() == 0){
        app.contacts().createContact(new ContactData()
                .withFirstname("Dont")
                .withLastname("Delete")
                .withNickname("Me")
                .withEmail("Please@email.com")
                .withBday("7")
                .withBmonth("December")
                .withByear("2007"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();
        int NewContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, NewContactCount);
    }

    @Test
    public void removeAtOnceContact(){
        if(app.contacts().getCount() == 0){
            app.contacts().createContact(new ContactData()
                    .withFirstname("Dont")
                    .withLastname("Delete")
                    .withNickname("Me")
                    .withEmail("Please@email.com")
                    .withBday("7")
                    .withBmonth("December")
                    .withByear("2007"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(1, app.contacts().getCount());
    }
}