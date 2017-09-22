package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void TestCreationContact() {
    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactForm(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122"));
    app.getContactHelper().submitNewContact();
    app.getContactHelper().returnToHome();
  }

}
