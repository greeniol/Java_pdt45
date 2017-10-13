package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().returnToHome();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData().withName("First").withLastname("Contact").withAddress("Street home 88")
              .withMail("mail@mail.con").withPhone("74445551122").withGroup("Test2"), true);
      app.goTo().returnToHome();
    }
  }

  @Test
  //редактирование контакта с основной формы
  public void testContactModificationMainForm() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Second").withLastname("ContactEdit").withAddress("Street home 88")
            .withMail("mail@mail.con").withPhone("74445551122");
    app.contact().modify(contact);
    app.goTo().returnToHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


  @Test
  //редактирование контакта через Details
  public void testContactModificationDetailsForm() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContactDetails = before.iterator().next();
    ContactData contact=  new ContactData().withId(modifiedContactDetails.getId()).withName("Third").withLastname("ContactEditDetails").withAddress("Street home 88")
            .withMail("mail@mail.con").withPhone("74445551122");
    app.contact().modifyDetails(contact);
    app.goTo().returnToHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContactDetails);
    before.add(contact);
    Assert.assertEquals(before, after);

  }


}
