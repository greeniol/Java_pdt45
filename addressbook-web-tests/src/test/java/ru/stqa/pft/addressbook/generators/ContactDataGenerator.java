package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description ="Group count")
  public  int count;

  @Parameter(names = "-f", description ="Target file")
  public String file;

  @Parameter(names = "-d", description ="Data format")
  public String format;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator= new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
   if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    }else{
      System.out.println("unrecognized format "+format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json =gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts =new ArrayList<>();
    for (int i=0; i<count; i++){
      contacts.add(new ContactData().withName(String.format("First %s",i)).withLastname(String.format("Contact %s",i))
              .withAddress(String.format("Street\nhome\n88\nnew address №%s",i))
              .withMail(String.format("mail%s@mail.con",i)).withMail2(String.format("ree_rfsf@fjfjf%s.re",i)).withMail3(String.format("EW2ff%s@gdg.com",i))
              .withHomephone(String.format("+7(444)5551%s",i)).withMobilephone(String.format("787-333%s",i)).withWorkphone(String.format("74 66 6%s",i))
              .withGroup(String.format("Test2")));
    }
    return contacts;
  }

}
