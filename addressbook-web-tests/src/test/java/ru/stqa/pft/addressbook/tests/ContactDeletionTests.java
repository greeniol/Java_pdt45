package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().CreateContact(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test99"), true);
    }
    app.getNavigationHelper().returnToHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().switchAlert();
  }

}
