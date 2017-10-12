package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.goTo().returnToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().CreateContact(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test2"), true);
    }
    app.goTo().returnToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().switchAlert();
    app.goTo().returnToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size()-1);
    Assert.assertEquals(before,after);
  }

}
