package ru.x5.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTests {
    @Test
    void calTriangle() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        double expectedArea = 6.0;
        double expectedPerimeter = 12.0;
        double actualArea = triangle.calculateArea();
        double actualPerimeter = triangle.perimeter();
        assertEquals(expectedArea, actualArea);
        assertEquals(expectedPerimeter, actualPerimeter);
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
    
    @Test
    void testTriangleEquality(){
        Triangle t1 = new Triangle(3.0,4.0,5.0);
        Triangle t2 = new Triangle(5.0,3.0,4.0);

        Assertions.assertEquals(t1.hashCode(), t2.hashCode());
    }

}
