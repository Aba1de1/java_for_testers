package tests;

import common.Common;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class RemoveContactTest extends TestBase {

    @Test
    public void canRemoveContact() {
        if(app.hmb().getContactCount() == 0){
        app.hmb().createContact(new ContactData()
                .withFirstname("Dont")
                .withLastname("Delete")
                .withNickname("Me")
                .withEmail("Please@email.com")
                .withBday("7")
                .withBmonth("December")
                .withByear("2007"));
        }
        var oldContacts = app.hmb().getContactList();
        var contactToRemove = oldContacts.get(0);
        app.contacts().removeContact(contactToRemove);
        var newContacts = app.hmb().getContactList();
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

    @Test
    public void removeContactFromGroup(){
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(
                    new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hmb().getGroupList().get(0);
        var oldRelated = app.hmb().getContactsIngroup(group);
        if (oldRelated.isEmpty()) {
            var newContact = new ContactData()
                    .withFirstname(Common.randomString(10))
                    .withLastname(Common.randomString(10));
            app.contacts().createB(newContact, group);
            oldRelated = app.hmb().getContactsIngroup(group);

        }
        var contactToRemove = oldRelated.get(0);
        app.contacts().removed(contactToRemove, group);
        var newRelated = app.hmb().getContactsIngroup(group);
        oldRelated.remove(contactToRemove);
        Assertions.assertEquals(newRelated, oldRelated);
    }
}