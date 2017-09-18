package ru.stqa.pft.sandbox;

public class FirstProgramm {
  public static void main(String[] args) {
    hello("world");
    hello("baby");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной, " + s.l + " = " + s.area());


    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь квадрата со сторонaми " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello(String some) {
    System.out.println("Hello, " + some + "!");
  }

}
