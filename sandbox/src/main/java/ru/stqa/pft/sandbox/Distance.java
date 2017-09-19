package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {

    Point x = new Point(3, 5);
    Point y = new Point(6, 9);

    //вызов функции
    System.out.println("Расстояние между точками (функция) "+ "x "+ x.x + ";"+ x.y + " и "+"y "+ y.x + ";"+ y.y + " = " + distance(x,y));

    //вызов метода
    System.out.println("Расстояние между точками (метод) "+ "x "+ x.x + ";"+ x.y + " и "+"y "+ y.x + ";"+ y.y + " = " + y.getDistance(x));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x)+(p2.y -p1.y)*(p2.y - p1.y));
  }
}