package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class RemoveContactTest extends TestBase {

    @Test
    public void canRemoveContact() {
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
        var oldContacts = app.contacts().getListContacts();
        var contactToRemove = oldContacts.get(0);
        app.contacts().removeContact(contactToRemove);
        var newContacts = app.contacts().getListContacts();
        oldContacts.remove(contactToRemove);
        Assertions.assertEquals(newContacts, oldContacts);
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
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}