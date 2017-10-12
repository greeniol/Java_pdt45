package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void switchAlert() {
    wd.switchTo().alert().accept();
  }

  public void modifyContactMainForm(int index) {
    wd.findElements(By.xpath("//img[contains(@src,'icons/pencil.png')]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void goToDetails (int index) {
    wd.findElements(By.xpath("//img[contains(@src,'icons/status_online.png')]")).get(index).click();;
  }

  public void modifyContactDetailsForm() {
    click(By.name("modifiy"));
  }


  public void create(ContactData contact, boolean b) {
    addNewContact();
    fillContactForm((contact), true);
    submitNewContact();
  }

  public void modify(int index, ContactData contact) {
   modifyContactMainForm(index);
   fillContactForm(contact, false);
   submitContactModification();
  }

  public void delete(int index) {
    selectContact(index);
    deleteContact();
    switchAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));

  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
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

