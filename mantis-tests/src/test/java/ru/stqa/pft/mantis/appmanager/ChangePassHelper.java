package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import javax.mail.MessagingException;
import java.io.IOException;

public class ChangePassHelper extends HelperBase {

  public ChangePassHelper(ApplicationManager app) {
    super(app);
  }


  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"),username);
    type(By.name("password"),password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void modifyPass (String changeuser, String username, String password) {
    login(username, password);
    click(By.linkText("Manage"));
    click(By.linkText("Manage Users"));
    click(By.linkText(changeuser));
    click(By.cssSelector("input[value='Reset Password']"));
    click(By.linkText("Logout"));
  }

  public void change(String confirmationLink, String passwordChanged) {
    wd.get(confirmationLink);
    type(By.name("password"),passwordChanged);
    type(By.name("password_confirm"),passwordChanged);
    click(By.cssSelector("input[value='Update User']"));
  }

}
