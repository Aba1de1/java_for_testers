package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private final SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withId("" + record.id)
                .withFirstname(record.firstname)
                .withLastname(record.lastname)
                .withAddress(record.address)
                .withEmail(record.email);
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstname(), data.lastname(), data.address(), data.email());
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.openSession().createQuery("from GroupRecord", GroupRecord.class).list());
    }

    public long getGroupCount() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        }
    }

    public void createGroup(GroupData groupData) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(convert(groupData));
            transaction.commit();
        }
    }

    public List<ContactData> getContactsIngroup(GroupData group) {
        try (var session = sessionFactory.openSession()) {
            return convertContactList(session.find(GroupRecord.class, group.id()).contacts);
        }
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.openSession().createQuery("from ContactRecord", ContactRecord.class).list());
    }

    public long getContactCount() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        }
    }

    public void createContact(ContactData contactData) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(convert(contactData));
            transaction.commit();
        }
    }
}