package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper  extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);

  }

  public void returnToHome() {
    click(By.linkText("home"));
  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("email"),contactData.getMail());
    type(By.name("home"),contactData.getPhone());
}

  public void addNewContact() {
    click(By.linkText("add new"));
  }
}
