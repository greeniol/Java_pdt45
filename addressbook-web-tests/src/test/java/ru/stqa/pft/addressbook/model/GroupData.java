package ru.stqa.pft.addressbook.model;

public class
GroupData {
  private int id;
  private final String groupname;
  private final String header;
  private final String footer;

  public GroupData( String groupname, String header, String footer) {
    this.id = 0;
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(int id, String groupname, String header, String footer) {
    this.id = id;
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", groupname='" + groupname + '\'' +
            '}';
  }


  public void setId(int id) {
    this.id = id;
  }
}
