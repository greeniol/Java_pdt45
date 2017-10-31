package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().returnToHome();
    if (app.contact().all().size() == 0) {
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
  public void testContactData() {
    app.goTo().returnToHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
    assertThat(cleanedAddress(contact.getAddress()), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDataTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeMails(ContactData contact) {
    return Arrays.asList(contact.getMail(), contact.getMail2(), contact.getMail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanedAddress(String address) {
    return address.replaceAll("\\s", "");
  }
}
