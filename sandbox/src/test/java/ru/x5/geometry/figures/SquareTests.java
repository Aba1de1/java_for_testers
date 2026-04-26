package ru.x5.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {


    @Test
    void canCalculateArea() {
        var s = new Square(5.0);
        double result = s.area();
        Assertions.assertEquals(25.0, result);
    }
    @Test
    void calCalculatePerimeter(){
        Assertions.assertEquals(20.0, new  Square(5.0).perimeter());
    }

    @Test
    void calTriangle(){
        Triangle triangle = new Triangle(3.0,4.0,5.0);
        double expectedArea = 6.0;
        double expectedPerimeter = 12.0;
        double actualArea = triangle.calculateArea();
        double actualPerimeter = triangle.perimeter();
        Assertions.assertEquals(expectedArea,actualArea);
        Assertions.assertEquals(expectedPerimeter, actualPerimeter);
    }

    @Test
    void cannotCreateSquareWithNegativeSide(){
        try { new Square(-5.0);
            Assertions.fail();
    } catch (IllegalArgumentException exception){
            //ok
        }
    }
}



