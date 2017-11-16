package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactInGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditionsContacts() {
    if (app.db().contacts().size() == 0) {
      app.goTo().returnToHome();
      app.contact().create(new ContactData().withName("First").withLastname("Contact").withAddress("Street home 88")
              .withMail("mail@mail.con").withHomephone("+7(444)55511").withMobilephone("787-3333").withWorkphone("74 66 61"), false);
      app.goTo().returnToHome();
    }
  }

  @BeforeMethod
  public void ensurePreconditionsGroups() {
    if (app.db().groups().size() == 0 ) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withGroupname("Test 2"));
      app.goTo().returnToHome();
    }
  }

  public static void newGroup() {
    long now = System.currentTimeMillis();
    String groupname = String.format("Group %s",now);
    app.goTo().GroupPage();
    app.group().create(new GroupData().withGroupname(groupname));
    app.goTo().returnToHome();
  }

  @Test
  public static void testContactInGroup() {
    Groups allgroups = app.db().groups();
    Iterator<GroupData> groups = allgroups.iterator();
    Contacts contact = app.db().contacts();
    ContactData contactModifyGroups = contact.iterator().next();
    Groups contactGroupBefore = contactModifyGroups.getGroups();
    GroupData group = null;
    if (contactModifyGroups.getGroups().size() != allgroups.size()) {
      while (groups.hasNext()) {
        group = groups.next();
        if (!contactModifyGroups.getGroups().contains(group)) {
          app.contact().pushInGroup(contactModifyGroups, group);
          app.goTo().returnToHome();
          break;
        }
      }
    } else if (contactModifyGroups.getGroups().size() == allgroups.size()){
    newGroup();
    groups = app.db().groups().iterator();
    while (groups.hasNext()) {
      group = groups.next();
      if (!contactModifyGroups.getGroups().contains(group)) {
        app.contact().pushInGroup(contactModifyGroups, group);
        app.goTo().returnToHome();
        break;
      }
     }
    }
    Groups contactGroupAfter= app.db().groupsInContact(contactModifyGroups.getId());
    assertThat(contactGroupAfter, equalTo(contactGroupBefore.withAdded(group)));
  }
}

