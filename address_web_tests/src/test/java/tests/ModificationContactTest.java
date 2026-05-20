package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class ModificationContactTest extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstname("You'Can")
                    .withLastname("Modify")
                    .withNickname("Me")
                    .withEmail("Please@email.com")
                    .withBday("7")
                    .withBmonth("December")
                    .withByear("2007"));
        }
        var oldContacts = app.contacts().getListContacts();
        var contactToModify = oldContacts.get(0);
        var modifyData = new ContactData()
                .withFirstname("I was")
                .withLastname("Modified")
                .withEmail("modifyDone@email.com");
        app.contacts().modifyContact(contactToModify, modifyData);
        var newContacts = app.contacts().getListContacts();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(modifyData);
        Comparator<ContactData> compareByName = Comparator
                .comparing(ContactData::firstname, Comparator.nullsFirst(String::compareTo))
                .thenComparing(ContactData::lastname, Comparator.nullsFirst(String::compareTo));
        expectedList.sort(compareByName);
        newContacts.sort(compareByName);
        Assertions.assertEquals(expectedList, newContacts);
    }
}
