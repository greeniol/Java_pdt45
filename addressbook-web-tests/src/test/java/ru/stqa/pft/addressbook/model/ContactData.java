package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String address;
  private final String mail;
  private final String phone;

  public ContactData(String name, String lastname, String address, String mail, String phone) {
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.mail = mail;
    this.phone = phone;
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

  public String getPhone() {
    return phone;
  }
}
