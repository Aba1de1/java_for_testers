package ru.x5.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, squareArea(side));
        System.out.println(text);
    }

    public static double squareArea(double a) {
        return a * a;
    }
}
