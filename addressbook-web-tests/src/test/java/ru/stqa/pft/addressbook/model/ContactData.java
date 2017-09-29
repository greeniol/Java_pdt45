package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String address;
  private final String mail;
  private final String phone;
  private String group;

  public ContactData(String name, String lastname, String address, String mail, String phone, String group) {
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.mail = mail;
    this.phone = phone;
    this.group = group;
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

  public String getGroup() {
    return group;
  }
}
