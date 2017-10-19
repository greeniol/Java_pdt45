package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().returnToHome();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("First").withLastname("Contact")
              .withAddress("Street home 88")
              .withMail("mail@mail.con").withMail2("ree_rfsf@fjfjf.re").withMail3("EW2ff@gdg.com")
              .withHomephone("+7(444)55511").withMobilephone("787-3333").withWorkphone("74 66 61")
              .withGroup("Test2"), true);
      app.goTo().returnToHome();
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().returnToHome();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));

  }


}
