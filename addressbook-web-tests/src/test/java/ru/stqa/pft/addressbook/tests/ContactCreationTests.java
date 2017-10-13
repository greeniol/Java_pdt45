package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void TestCreationContact() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withName("First").withLastname("Contact").withAddress("Street home 88")
            .withMail("mail@mail.con").withPhone("74445551122").withGroup("Test2");
    app.contact().create(contact, true);
    app.goTo().returnToHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
