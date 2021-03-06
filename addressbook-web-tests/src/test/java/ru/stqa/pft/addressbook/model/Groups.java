package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups(Collection<GroupData> groups) {
    this.delegate = new HashSet<GroupData>(groups);
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  public Groups without(GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

/*  boolean contains(GroupData group) {
    for (GroupData groupData: delegate) {
      if (groupData.getId() == group.getId()) {
        return true;
      }
    }
    return false;
  }*/
}
