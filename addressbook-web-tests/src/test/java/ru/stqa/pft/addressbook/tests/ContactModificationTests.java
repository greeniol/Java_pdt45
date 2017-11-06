package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().returnToHome();
      app.contact().create(new ContactData().withName("First").withLastname("Contact").withAddress("Street home 88")
              .withMail("mail@mail.con").withHomephone("+7(444)55511").withMobilephone("787-3333").withWorkphone("74 66 61"), true);
      app.goTo().returnToHome();
    }
  }

  @Test
  //редактирование контакта с основной формы
  public void testContactModificationMainForm() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withName("Second").withLastname("ContactEdit")
            .withAddress("Street " + "              " +
                    "home                      " +
                    "88           " +
                    "fajwerawraiwerqottttttttttttttttttt")
            .withMail("mail@mail.con").withMail2("ree_rfsf@fjfjf.re").withMail3("EW2ff@gdg.com")
            .withHomephone("+7(444)55511").withMobilephone("787-3333").withWorkphone("74 66 61");
    app.contact().modify(contact);
    app.goTo().returnToHome();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


  @Test
  //редактирование контакта через Details
  public void testContactModificationDetailsForm() {
    Contacts before = app.db().contacts();
    ContactData modifiedContactDetails = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContactDetails.getId())
            .withName("Third").withLastname("ContactEditDetails")
            .withAddress("Street " + "              " +
                    "home                      " +
                    "88           " +
                    "fajwerawraiwerqottttttttttttttttttt")
            .withMail("mail@mail.con").withMail2("ree_rfsf@fjfjf.re").withMail3("EW2ff@gdg.com")
            .withHomephone("+7(444)55511").withMobilephone("787-3333").withWorkphone("74 66 61");
    app.contact().modifyDetails(contact);
    app.goTo().returnToHome();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    before.remove(modifiedContactDetails);
    before.add(contact);
    assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedContactDetails).withAdded(contact)));

  }


}
