package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().returnToHome();
      app.contact().create(new ContactData()
                      .withName("First").withLastname("Contact")
                      .withAddress("Street " + "              " +
                              "home                      " +
                              "88           " +
                              "fajwerawraiwerqottttttttttttttttttt")
                      .withMail("mail@mail.con").withMail2("ree_rfsf@fjfjf.re").withMail3("EW2ff@gdg.com")
                      .withHomephone("+7(444)55511").withMobilephone("787-3333").withWorkphone("74 66 61")
              , false);
      app.goTo().returnToHome();
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().returnToHome();
    assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));

  }


}
