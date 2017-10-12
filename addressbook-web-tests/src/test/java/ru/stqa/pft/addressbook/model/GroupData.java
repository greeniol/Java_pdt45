package ru.stqa.pft.addressbook.model;

public class
GroupData {
  private int id= Integer.MAX_VALUE;
  private  String groupname;
  private  String header;
  private  String footer;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;
  }

  @Override
  public int hashCode() {
    return groupname != null ? groupname.hashCode() : 0;
  }


  public String getGroupname() {
    return groupname;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", groupname='" + groupname + '\'' +
            '}';
  }


  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withGroupname(String groupname) {
    this.groupname = groupname;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }
}
