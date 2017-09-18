package ru.stqa.pft.sandbox;

public class Distance {

    public double x;
    public double y;

  public Distance(double x, double y)
  {
    this.x=x;
    this.y=y;
  }

  public double getdistance(Distance p) {
    return Math.sqrt((x - p.x)*(x - p.x)+(y - p.y)*(y - p.y));
  }

  }


