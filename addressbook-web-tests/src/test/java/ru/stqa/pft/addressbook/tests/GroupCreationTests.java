package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void TestCreationGroup() {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withGroupname("Test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

}
