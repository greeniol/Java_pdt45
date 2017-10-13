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
    type(By.name("home"), contactData.getPhone());
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
    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']"))
            .findElement(By.xpath("img[contains(@src,'icons/pencil.png')]")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void goToDetailsById (int id) {
    wd.findElement(By.cssSelector("a[href='view.php?id="+id+"']"))
            .findElement(By.xpath("img[contains(@src,'icons/status_online.png')]")).click();
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
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable>tbody>tr[name|='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = element.findElement(By.xpath("./td[2]/following-sibling::td")).getText();
      String lastname =  element.findElement(By.xpath("./td[1]/following-sibling::td")).getText();
      contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
    }
    return contacts;
  }

}

