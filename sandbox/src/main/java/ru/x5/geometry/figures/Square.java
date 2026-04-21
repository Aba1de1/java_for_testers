package ru.x5.geometry.figures;

public record Square (double side){

    public static void printSquareArea(Square s) {
        String text = String.format("Площадь квадрата со стороной %.1f = %.1f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter(){
        return 4 * this.side;
    }
}
