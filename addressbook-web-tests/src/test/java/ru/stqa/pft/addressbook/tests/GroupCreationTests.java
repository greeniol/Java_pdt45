package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{new GroupData().withGroupname("Test1").withHeader("Header 1").withFooter("Footer 1")});
    list.add(new Object[]{new GroupData().withGroupname("Test2").withHeader("Header 2").withFooter("Footer 2")});
    list.add(new Object[]{new GroupData().withGroupname("Test3").withHeader("Header 3").withFooter("Footer 3")});
    return  list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void TestCreationGroup(GroupData group) {
      app.goTo().GroupPage();
      Groups before = app.group().all();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
      assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void TestBadCreationGroup() {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withGroupname("Test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size()));

  }

}
