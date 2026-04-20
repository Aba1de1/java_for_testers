package ru.x5.geometry;

import ru.x5.geometry.figures.Rectangle;
import ru.x5.geometry.figures.Square;
import ru.x5.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square (5.0));
        Square.printSquareArea(new Square (3.0));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);


        Triangle.printTriangleArea(new Triangle(3.0, 4.0, 5.0));
        Triangle.printTriangleArea(new Triangle(6.0, 8.0, 10.0));
        Triangle.printTriangleArea(new Triangle(5.0, 5.0, 6.0));
    }

}
