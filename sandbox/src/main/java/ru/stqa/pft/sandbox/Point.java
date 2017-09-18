package ru.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args) {

    Distance x = new Distance(3, 5);
    Distance y = new Distance(6, 9);

    //вызов функции
    System.out.println("Расстояние между точками (функция) "+ "x "+ x.x + ";"+ x.y + " и "+"y "+ y.x + ";"+ y.y + " = " + distance(x,y));

    //вызов метода
    System.out.println("Расстояние между точками (метод) "+ "x "+ x.x + ";"+ x.y + " и "+"y "+ y.x + ";"+ y.y + " = " + y.getdistance(x));
  }

  public static double distance(Distance p1, Distance p2) {
    return Math.sqrt(((p2.x - p1.x)*(p2.x - p1.x))+((p2.y -p1.y)*(p2.y - p1.y)));
  }
}