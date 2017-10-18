package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().returnToHome();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withName("First").withLastname("Contact").withAddress("Street home 88")
              .withMail("mail@mail.con").withHomephone("74445551122").withMobilephone("787113333").withWorkphone("74166361").withGroup("Test2"), true);
      app.goTo().returnToHome();
    }
  }
  @Test
  public void testContactPhones(){
    app.goTo().returnToHome();
    ContactData contact =app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
