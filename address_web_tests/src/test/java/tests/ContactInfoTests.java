package tests;

import model.ContactData;
import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    static Stream<Arguments> contactProvider(){
        return Stream.of(
                Arguments.of("phones",(Function<ContactData, Stream<String>>) contact ->
                        Stream.of(contact.home(), contact.mobile(), contact.work()),
                Arguments.of("emails",(Function<ContactData, Stream<String>>) contact ->
                        Stream.of(contact.email(), contact.email2(), contact.email3())),
                Arguments.of("address", (Function<ContactData, Stream<String>>) contact ->
                        Stream.of(contact.address())))
        );
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    void testContactProvider(String type, Function<ContactData, Stream<String>> dataExtractor){
        var contacts = app.hmb().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                dataExtractor.apply(contact)
                        .filter(s -> s !=null && !s.isEmpty())
                        .collect(Collectors.joining(" "))));
        Map<String, String> actual;
        if ("phones".equals(type)) {
            actual = app.contacts().getPhones();
        } else if ("emails".equals(type)) {
            actual = app.contacts().getEmails();
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        var normalized = actual.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                        entry.getValue().replaceAll("\\s+", " ").trim()));
        Assertions.assertEquals(expected, normalized, String.format("Данные %s не совпадают", type));
    }



    @Test
    void testPhones() {
        var contacts = app.hmb().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

}
