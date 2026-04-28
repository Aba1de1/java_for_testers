package ru.x5.geometry.figures;

public record Triangle(double a, double b, double c) {

    public Triangle{
        if (a <= 0.0 || b <= 0.0 || c <= 0.0){
            throw new IllegalArgumentException(
                    String.format("Сторона треугольника должна быть положительной. Получено: a=%.1f, b=%.1f, c=%.1f", a, b, c));
        }
        if (!(a + b > c && a + c > b && b + c > a)){
            throw new IllegalArgumentException(
                    String.format("Нарушение неравенства треугольника: стороны %.1f, %.1f, %.1f не могут образовать треугольник.", a, b, c));
        }
    }

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

