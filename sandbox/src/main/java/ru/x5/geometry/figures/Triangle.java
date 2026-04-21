package ru.x5.geometry.figures;

public record Triangle(double a, double b, double c) {

    public static void printTriangleArea(Triangle t){
        String text = String.format(
                "Периметр треугольника = %.1f. Площадь сторон треугольника %.1f, %.1f, %.1f = %.1f",
                t.perimeter(),t.a(), t.b(), t.c(), t.calculateArea());
        System.out.println(text);
    }

    public double calculateArea(){
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    public double perimeter(){

        return a + b + c;
    }
}

