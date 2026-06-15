package model;

import java.util.Objects;

public class ContactData {
    public String address;
    private String id;
    private String photo;
    private String firstname;
    private String lastname;
    private String nickname;
    private String email;
    private String email2;
    private String email3;
    private String bday;
    private String bmonth;
    private String byear;
    private String home;
    private String mobile;
    private String work;

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public ContactData withId(String id) {
        this.id = id;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String firstname() {
        return firstname;
    }

    public String lastname() {
        return lastname;
    }

    public String nickname() {
        return nickname;
    }

    public String email() { return email;}

    public String email2() {
        return email2;
    }

    public String email3() {
        return email3;
    }

    public String bday() {
        return bday;
    }

    public String bmonth() {
        return bmonth;
    }

    public String byear() { return byear; }

    public String photo() {
        return photo;
    }

    public String id() {
        return id;
    }

    public String address() {
        return address;
    }

    public String home() {
        return home;
    }

    public String work() {
        return work;
    }


    public String mobile() {
        return mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id(), that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id());
    }
}