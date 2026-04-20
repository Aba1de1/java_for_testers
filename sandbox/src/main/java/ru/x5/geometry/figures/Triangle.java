package ru.x5.geometry.figures;

public record Triangle(double a, double b, double c) {

    public static void printTriangleArea(Triangle t){
        String text = String.format(
                "Площадь треугольника со сторонами %.1f, %.1f, %.1f = %.1f",
                t.a(), t.b(), t.c(), t.calculateArea());
        System.out.println(text);
    }

    public double calculateArea(){
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}

