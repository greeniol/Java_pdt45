package ru.stqa.pft.addressbook.model;

public class
GroupData {
  private final String id;
  private final String groupname;
  private final String header;
  private final String footer;

  public GroupData( String groupname, String header, String footer) {
    this.id = null;
    this.groupname = groupname;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(String id, String groupname, String header, String footer) {
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

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != null ? !id.equals(groupData.id) : groupData.id != null) return false;
    return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
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


}
