package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  //редактирование контакта с основной формы
  public void testContactModificationMainForm() {
    app.getNavigationHelper().returnToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().CreateContact(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test99"), true);
    }
    app.getNavigationHelper().returnToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().modifyContactMainForm();
    app.getContactHelper().fillContactForm(new ContactData("First", "ContactEdit", "Street home", "mail@mail.com", "74411151122", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }

  @Test
  //редактирование контакта через Details
  public void testContactModificationDetailsForm() {
    app.getNavigationHelper().returnToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().CreateContact(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test99"), true);
    }
    app.getNavigationHelper().returnToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().goToDetails();
    app.getContactHelper().modifyContactDetailsForm();
    app.getContactHelper().fillContactForm(new ContactData("First", "ContactEdit", "Street home", "mail@mail.com", "74411151122", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

  }

}
