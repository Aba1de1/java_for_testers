package manager.hbm;

import jakarta.persistence.*;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "address")
    public String address;
    @Column(name = "email")
    public String email;
    @Transient
    public String middlename;
    @Transient
    public String nickname;
    @Transient
    public String company;
    @Transient
    public String title;
    @Transient
    public String home;
    @Transient
    public String mobile;
    @Transient
    public String work;
    @Transient
    public String fax;
    @Transient
    public String email2;
    @Transient
    public String email3;
    @Transient
    public String homepage;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String address, String email) {
        this.id = id;

        this.firstname = firstname;

        this.lastname = lastname;

        this.address = address;

        this.email = email;
    }

}

