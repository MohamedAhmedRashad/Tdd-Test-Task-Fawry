package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testShouldFailedDuoToNoMethodIsImplemented() {
        // Arrange
        Calculator calculator=new Calculator();

        // Act
        int result=calculator.max(3,7);


        //Assert

        Assertions.assertEquals(7,result);

    }


}