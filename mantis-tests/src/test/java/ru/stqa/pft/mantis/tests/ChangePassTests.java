package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ChangePassTests extends TestBase {
  long now = System.currentTimeMillis();
  String email = String.format("user%s@localhost",now);
  String user = String.format("user%s",now);
  String password = "password";


  @BeforeMethod
  public void testRegistration() throws IOException, MessagingException {
    app.james().createUser(user,password);
    app.registration().start(user, email);

  }

  @Test
  public void testChangePass() throws IOException, MessagingException {
    String passwordChanged ="changed_pass";
    app.changePass().modifyPass(user,app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword") );
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 70000);
    String confirmationLink = findConfirmationLink(mailMessages,email);
    app.changePass().change(confirmationLink, passwordChanged);
    assertTrue(app.newSession().login(user,passwordChanged));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m)->m.text.contains(String.format("Someone"))).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
