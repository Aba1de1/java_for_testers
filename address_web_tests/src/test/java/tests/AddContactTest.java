package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
            result.add(new ContactData().withFirstname(randomString(10)).withLastname(randomString(10)).withEmail(randomEmail()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreatedMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}