package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
  @Test
  public void testDistanceFirst() {
    Point x = new Point(3,5);
    Point y = new Point(6,9);

    Assert.assertEquals(x.getDistance(y),5.0);

  }
@Test
  public void testDistanceSecond() {
    Point x = new Point(11,3);
    Point y = new Point(26,39);

    Assert.assertEquals(x.getDistance(y),39.0);

  }
}
