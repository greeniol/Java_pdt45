package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactOutGroup extends TestBase  {

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
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withGroupname("Test 2"));
      app.goTo().returnToHome();
    }
  }

  @BeforeMethod
  public void ensurePreconditionsInGroups() {
    if (groupsWithContact() == 0) {
      ContactInGroup.testContactInGroup();
    }
  }

  public static int groupsWithContact() {
    Groups groupsall = app.db().groups();
    int groupsWithContact = 0;
    Iterator<GroupData> groups = groupsall.iterator();
    while (groups.hasNext()) {
      GroupData group = groups.next();
      if (group.getContactDataSet().size() != 0) {
        groupsWithContact = ++groupsWithContact;
      }
    }
    return groupsWithContact;
  }

  @Test
  public static void testContactOutGroup() {
    Groups before = app.db().groups();
    Contacts modifiedGroupAfter = null;
    int modifiedGroupID =0;
    Iterator<GroupData> groups = before.iterator();
    while (groups.hasNext()) {
      GroupData group = groups.next();
      if (group.getContactDataSet().size() != 0) {
        int contact = group.getContactDataSet().iterator().next().getId();
        app.group().outOfGroup(group, contact);
        modifiedGroupID = group.getId();
          modifiedGroupAfter = app.db().contactInGroups(modifiedGroupID);
          Assert.assertTrue(!modifiedGroupAfter.contains(group));
        break;
          }
      }
    Groups after = app.db().groups();
    assertEquals(before.size(), after.size());
    assertThat(app.contact().count(), equalTo(modifiedGroupAfter.size())) ;


  }

}
