package ru.x5.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void calTriangle() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        double expectedArea = 6.0;
        double expectedPerimeter = 12.0;
        double actualArea = triangle.calculateArea();
        double actualPerimeter = triangle.perimeter();
        Assertions.assertEquals(expectedArea, actualArea);
        Assertions.assertEquals(expectedPerimeter, actualPerimeter);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 5.0, 6.0);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithDifferedSides() {
        try {
            new Triangle(2.0, 3.0, 11.0);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
