package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  //редактирование контакта с основной формы
  public void testContactModificationMainForm() {
    app.getNavigationHelper().returnToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().CreateContact(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test2"), true);
    }
    app.getNavigationHelper().returnToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().modifyContactMainForm(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Second", "ContactEdit", "Street home", "mail@mail.com", "74411151122", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2)-> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  //редактирование контакта через Details
  public void testContactModificationDetailsForm() {
    app.getNavigationHelper().returnToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().CreateContact(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test1"), true);
    }
    app.getNavigationHelper().returnToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().goToDetails(before.size()-1);
    app.getContactHelper().modifyContactDetailsForm();
     ContactData contact= new ContactData(before.get(before.size()-1).getId(),"First", "ContactEdit", "Street home", "mail@mail.com", "74411151122", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2)-> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
