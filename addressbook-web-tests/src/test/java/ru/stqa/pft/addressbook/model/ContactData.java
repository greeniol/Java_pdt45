package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final int id;
  private final String name;
  private final String lastname;
  private final String address;
  private final String mail;
  private final String phone;
  private String group;

  public ContactData(int id,String name, String lastname, String address, String mail, String phone, String group) {
    this.id=id;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.mail = mail;
    this.phone = phone;
    this.group = group;
  }

  public ContactData(String name, String lastname, String address, String mail, String phone, String group) {
    this.id=Integer.MAX_VALUE;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.mail = mail;
    this.phone = phone;
    this.group = group;
  }

  public int getId() {
    return id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
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

