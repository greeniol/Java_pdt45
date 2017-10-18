package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);

  }


  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getMail());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void addNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void switchAlert() {
    wd.switchTo().alert().accept();
  }

  public void modifyContactMainFormById(int id) {
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void goToDetailsById (int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']",id))).click();
  }

  public void modifyContactDetailsForm() {
    click(By.name("modifiy"));
  }


  public void create(ContactData contact, boolean b) {
    addNewContact();
    fillContactForm((contact), true);
    submitNewContact();
  }

  public void modify(ContactData contact) {
   modifyContactMainFormById(contact.getId());
   fillContactForm(contact, false);
   submitContactModification();
  }

  public void modifyDetails(ContactData contact) {
    goToDetailsById(contact.getId());
    modifyContactDetailsForm();
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    switchAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));

  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells =element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lastname =  cells.get(1).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname)
              .withHomephone(phones[0]).withMobilephone(phones[1]).withWorkphone(phones[2]));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    modifyContactMainFormById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname)
            .withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone);
  }
}

