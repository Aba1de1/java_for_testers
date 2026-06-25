package tests;

import common.Common;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ModificationContactTest extends TestBase {
//    @Test
//    void canModifyContact() {
//        if (app.hmb().getContactCount() == 0) {
//            app.hmb().createContact(new ContactData()
//                    .withFirstname("You'Can")
//                    .withLastname("Modify")
//                    .withNickname("Me")
//                    .withEmail("Please@email.com")
//                    .withBday("7")
//                    .withBmonth("December")
//                    .withByear("2007"));
//        }
//        var oldContacts = app.hmb().getContactList();
//        var rnd = new Random();
//        var index = rnd.nextInt(oldContacts.size());
//        var modifyData = new ContactData()
//                .withFirstname("Modified")
//                .withLastname("I was")
//                .withEmail(Common.randomEmail());
//        app.contacts().modifyContact(index, modifyData);
//        var newContacts = app.hmb().getContactList();
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.set(index, modifyData);
//        Comparator<ContactData> compareByName = Comparator
//                .comparing(ContactData::firstname, Comparator.nullsFirst(String::compareTo))
//                .thenComparing(ContactData::lastname, Comparator.nullsFirst(String::compareTo));
//        newContacts.sort(compareByName);
//        expectedList.sort(compareByName);
//
//
//        Assertions.assertEquals(expectedList, newContacts);
//
//    }
}