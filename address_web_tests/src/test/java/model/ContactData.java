package model;

import java.util.Objects;

public class ContactData {
    private String photo;
    private String firstname;
    private String lastname;
    private String nickname;
    private String email;
    private String bday;
    private String bmonth;
    private String byear;

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
    public ContactData withPhoto(String photo){
        this.photo = photo;
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

    public String email() {
        return email;
    }

    public String bday() {
        return bday;
    }

    public String bmonth() {
        return bmonth;
    }

    public String byear() {
        return byear;
    }

    public String photo(){return photo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(bday, that.bday) &&
                Objects.equals(bmonth, that.bmonth) &&
                Objects.equals(byear, that.byear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, bday, bmonth, byear);
    }
}