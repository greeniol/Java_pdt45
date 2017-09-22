package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void TestCreationContact() {
    app.addNewContact();
    app.fillContactForm(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122"));
    app.submitNewContact();
    app.returnToHome();
  }

}
