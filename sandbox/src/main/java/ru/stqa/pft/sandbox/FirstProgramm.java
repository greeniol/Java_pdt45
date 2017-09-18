package ru.stqa.pft.sandbox;

public class FirstProgramm {
  public static void main(String[] args) {
    Hello("world");
    Hello("baby");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной, " + s.l + " = " + s.area());


    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь квадрата со сторонaми " + r.a + " и " + r.b + " = " + r.area());
  }

  public static void Hello(String some) {
    System.out.println("Hello, " + some + "!");
  }


}
