package ru.stqa.pft.rest;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TestBase {
  private final Properties properties;

  @BeforeClass
  public void token(){
    RestAssured.authentication=RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed","");
  }

  @BeforeSuite
  public void setUp() throws Exception {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src\\test\\resources\\%s.properties", target))));
  }


  public TestBase() {
    properties = new Properties();
  }


  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public  Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json?limit=400").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues") ;
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>() {
    }.getType());
  }


    public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
     throw new SkipException("Ignored because of issue " + issueId);
    }
  }


 private boolean isIssueOpen(int issueId)  {
   String json = RestAssured.given()
           .pathParam("issue_id",issueId)
           .get("http://demo.bugify.com/api/issues/{issue_id}.json").asString();
   JsonElement parsed = new JsonParser().parse(json);
   JsonElement issues = parsed.getAsJsonObject().get("issues");
   Set<Issue> newIssue = new Gson().fromJson(issues,new TypeToken<Set<Issue>>() {}.getType());


    if ( states!=2 || states!=3 ){
     return true;
   }
    return false;
  }






}
