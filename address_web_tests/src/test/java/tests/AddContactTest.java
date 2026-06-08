package tests;

import com.mysql.cj.exceptions.AssertionFailedException;
import common.Common;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddContactTest extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "First")) {
            for (var lastname : List.of("", "Last")) {
                for (var email : List.of("", "first.last@email.com")) {
                    result.add(new ContactData().withFirstname(firstname).withLastname(lastname).withEmail(email));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstname(randomContact(10))
                    .withLastname(randomContact(10))
                    .withEmail(Common.randomEmail()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreatedMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getListContacts();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getListContacts();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact);
        Comparator<ContactData> compareById = Comparator
                .comparing(ContactData::firstname, Comparator.nullsFirst(String::compareTo))
                .thenComparing(ContactData::lastname, Comparator.nullsFirst(String::compareTo));
        expectedList.sort(compareById);
        newContacts.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }


    @Test
    void canCreatedContactInGroup() {
        var contact = new ContactData().withFirstname(Common.randomString(10))
                .withLastname(Common.randomString(10));
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(
                    new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hmb().getGroupList().get(0);
        var oldRelated = app.hmb().getContactsIngroup(group);
        app.contacts().createB(contact, group);
        var newRelated = app.hmb().getContactsIngroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        contact.withId(newRelated.stream()
                .filter(contactData -> !oldRelated.contains(contactData))
                .findFirst()
                .orElseThrow(() -> new AssertionFailedException("Контакт не добавлен в группу"))
                .id());
        expectedList.add(contact);
        Comparator<ContactData> compareById = Comparator
                .comparing(ContactData::firstname, Comparator.nullsFirst(String::compareTo))
                .thenComparing(ContactData::lastname, Comparator.nullsFirst(String::compareTo));
        expectedList.sort(compareById);
        newRelated.sort(compareById);
        Assertions.assertEquals(expectedList, newRelated);
    }

    @Test
    void canAddContactInGroup(){
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
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(
                    new GroupData("", "group name", "group header", "group footer"));
        }
        var contact = app.hmb().getContactList().get(0);
        var group = app.hmb().getGroupList().get(0);
        var oldRelated = app.hmb().getContactsIngroup(group);
        app.contacts().addIntoGroup(contact, group);
        var newRelated = app.hmb().getContactsIngroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        contact.withId(newRelated.stream()
                .filter(contactData -> !oldRelated.contains(contactData))
                .findFirst()
                .orElseThrow(() -> new AssertionFailedException("Контакт не добавлен в группу"))
                .id());
        expectedList.add(contact);
        Comparator<ContactData> compareById = Comparator
                .comparing(ContactData::firstname, Comparator.nullsFirst(String::compareTo))
                .thenComparing(ContactData::lastname, Comparator.nullsFirst(String::compareTo));
        expectedList.sort(compareById);
        newRelated.sort(compareById);
        Assertions.assertEquals(expectedList, newRelated);
    }
}
