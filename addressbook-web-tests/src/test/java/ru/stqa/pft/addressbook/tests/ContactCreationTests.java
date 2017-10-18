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
    ContactData contact = new ContactData().withName("First").withLastname("Contact").withAddress("Street home 88")
            .withMail("mail@mail.con").withHomephone("74445551122").withMobilephone("787113333").withWorkphone("74166361").withGroup("Test2");
    app.contact().create(contact, true);
    app.goTo().returnToHome();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }

}
