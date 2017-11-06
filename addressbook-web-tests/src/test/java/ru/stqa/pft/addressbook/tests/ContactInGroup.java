package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactInGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditionsContacts() {
    if (app.db().contacts().size() == 0 || contactsWithFullGroups() == app.db().contacts().size()) {
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


  public static int contactsWithFullGroups() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    int contactsWithFullGroup = 0;
    Iterator<ContactData> contacts = before.iterator();
    while (contacts.hasNext()) {
      ContactData contact = contacts.next();
      if (contact.getGroups().size() == groups.size()) {
        contactsWithFullGroup = ++contactsWithFullGroup;
      }
    }
    return contactsWithFullGroup;
  }


  @Test
  public void testContactInGroup() {
    Groups allgroups = app.db().groups();
    Contacts before = app.db().contacts();
    Iterator<ContactData> contacts = before.iterator();
    Iterator<GroupData> groups = allgroups.iterator();
    while (contacts.hasNext()) {
      ContactData contact = contacts.next();
      if (contact.getGroups().size() != allgroups.size()) {
        while (groups.hasNext()) {
          GroupData group = groups.next();
          if (!contact.getGroups().contains(group))
          {app.contact().pushInGroup(contact, group);
            app.goTo().returnToHome();
            break;
          }
        }
        break;
      }
    }
    Contacts after = app.db().contacts();
    assertEquals(before.size(), after.size());
  }
}