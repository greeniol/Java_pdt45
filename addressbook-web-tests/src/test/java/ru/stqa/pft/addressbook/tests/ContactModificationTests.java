package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().returnToHome();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withName("First").withLastname("Contact").withAddress("Street home 88")
              .withMail("mail@mail.con").withPhone("74445551122").withGroup("Test2"), true);
      app.goTo().returnToHome();
    }
  }

  @Test
  //редактирование контакта с основной формы
  public void testContactModificationMainForm() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Second").withLastname("ContactEdit").withAddress("Street home 88")
            .withMail("mail@mail.con").withPhone("74445551122");
    app.contact().modify(contact);
    app.goTo().returnToHome();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


  @Test
  //редактирование контакта через Details
  public void testContactModificationDetailsForm() {
    Contacts before = app.contact().all();
    ContactData modifiedContactDetails = before.iterator().next();
    ContactData contact=  new ContactData().withId(modifiedContactDetails.getId()).withName("Third").withLastname("ContactEditDetails").withAddress("Street home 88")
            .withMail("mail@mail.con").withPhone("74445551122");
    app.contact().modifyDetails(contact);
    app.goTo().returnToHome();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    before.remove(modifiedContactDetails);
    before.add(contact);
    assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedContactDetails).withAdded(contact)));

  }


}
