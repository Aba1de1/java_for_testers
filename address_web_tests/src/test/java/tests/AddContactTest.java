package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
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
            result.add(new ContactData().withFirstname(randomContact(10)).withLastname(randomContact(10)).withEmail(randomEmail()));
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
        Comparator<ContactData> compareById = Comparator.comparing(ContactData::firstname, Comparator.nullsFirst(String::compareTo)).thenComparing(ContactData::lastname, Comparator.nullsFirst(String::compareTo));
        expectedList.sort(compareById);
        newContacts.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }
}