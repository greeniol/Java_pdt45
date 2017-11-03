package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactInGroup extends TestBase{
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
      app.group().create(new GroupData().withGroupname("Test2"));
    }
  }

  @Test
  public void testContactInGroup() {
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    if (contact.getGroups().size() != groups.size()) {
    //  before.remove(contact);
      //contact = before.iterator().next();

    if (contact.getGroups().contains(group)) {
      System.out.println("содержит");
      groups.remove(group);
      group = groups.iterator().next();
    }


      app.contact().pushInGroup(contact, group);
      app.goTo().returnToHome();
    }
    Contacts after = app.db().contacts();
    System.out.println(after);

  }
}
