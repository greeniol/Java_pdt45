package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().returnToHome();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("First", "Contact", "Street home 88", "mail@mail.con", "74445551122", "Test2"), true);
      app.goTo().returnToHome();
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    app.goTo().returnToHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before,after);
  }


}
