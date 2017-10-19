package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase {

  @Test
  public void TestCreationContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("First").withLastname("Contact")
            .withAddress("Street home 88")
            .withMail("mail@mail.con").withMail2("ree_rfsf@fjfjf.re").withMail3("EW2ff@gdg.com")
            .withHomephone("+7(444)55511").withMobilephone("787-3333").withWorkphone("74 66 61")
            .withGroup("Test2");
    app.contact().create(contact, true);
    app.goTo().returnToHome();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
