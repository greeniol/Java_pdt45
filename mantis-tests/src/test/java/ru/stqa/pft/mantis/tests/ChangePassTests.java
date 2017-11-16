package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ChangePassTests extends TestBase {


  @BeforeMethod
  public void testRegistration() throws IOException, MessagingException {
    if ( app.db().users().size()==0) {
      long now = System.currentTimeMillis();
      String emailcr = String.format("username%s@localhost",now);
      String usercr = String.format("username%s",now);
      String passwordcr = "password";
      app.james().createUser(usercr, passwordcr);
      app.registration().start(usercr, emailcr);
    }
  }
  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePass() throws IOException, MessagingException {
    String passwordChanged ="changed_pass";
    Users users = app.db().users();
    UserData userToChange = users.iterator().next();
    String email = userToChange.getEmail();
    String username = userToChange.getUsername();
    app.changePass().modifyPass(username,app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword") );
    List<MailMessage> mailMessages =app.mail().waitForMail(1,10000);
  //  List<MailMessage> mailMessages = app.james().waitForMail(username,String.format("password") , 70000);
    String confirmationLink = findConfirmationLink(mailMessages,email);
    app.changePass().change(confirmationLink, passwordChanged);
    assertTrue(app.newSession().login(username,passwordChanged));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m)->m.text.contains(String.format("Someone"))).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
