package ru.stqa.pft.addressbook.model;

import java.io.File;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String name;
  private String lastname;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;
  private String mail;
  private String mail2;
  private String mail3;
  private String allMails;
  private String group;
  private File photo;


  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withHomephone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilephone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkphone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allphones) {
    this.allPhones = allphones;
    return this;
  }

  public ContactData withMail(String mail) {
    this.mail = mail;
    return this;
  }

  public ContactData withMail2(String mail2) {
    this.mail2 = mail2;
    return this;
  }

  public ContactData withMail3(String mail3) {
    this.mail3 = mail3;
    return this;
  }

  public ContactData withAllMails(String allMails) {
    this.allMails = allMails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }


  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMail() {
    return mail;
  }

  public String getGroup() {
    return group;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getMail2() {
    return mail2;
  }

  public String getMail3() {
    return mail3;
  }

  public String getAllMails() {
    return allMails;
  }

  public File getPhoto() {
    return photo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}

