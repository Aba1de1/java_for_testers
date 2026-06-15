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
    @Column(name = "middlename")
    public String middlename;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "nickname")
    public String nickname;
    @Column(name = "company")
    public String company;
    @Column(name = "title")
    public String title;
    @Column(name = "address")
    public String address;
    @Column(name = "home")
    public String home;
    @Column(name = "mobile")
    public String mobile;
    @Column(name = "email")
    public String email;
    @Column(name = "work")
    public String work;
    @Column(name = "email2")
    public String email2;
    @Column(name = "email3")
    public String email3;
    @Column(name = "homepage")
    public String homepage;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String address, String email, String mobile) {
        this.id = id;

        this.firstname = firstname;

        this.lastname = lastname;

        this.address = address;

        this.email = email;

        this.mobile = mobile;
    }

}

