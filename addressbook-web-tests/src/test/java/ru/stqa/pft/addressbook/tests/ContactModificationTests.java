package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().returnToHome();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test2"), true);
      app.goTo().returnToHome();
    }
  }

  @Test
  //редактирование контакта с основной формы
  public void testContactModificationMainForm() {
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    ContactData contact = new ContactData(before.get(index).getId(),"Second", "ContactEdit", "Street home", "mail@mail.com", "74411151122", null);
    app.contact().modify(index, contact);
    app.goTo().returnToHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2)-> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


/*  @Test
  //редактирование контакта через Details
  public void testContactModificationDetailsForm() {
    List<ContactData> before = app.contact().list();
    app.contact().goToDetails(before.size()-1);
    app.contact().modifyContactDetailsForm();
     ContactData contact= new ContactData(before.get(before.size()-1).getId(),"First", "ContactEdit", "Street home", "mail@mail.com", "74411151122", null);
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.goTo().returnToHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2)-> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }*/

}
