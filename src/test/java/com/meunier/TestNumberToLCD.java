package com.meunier;

import org.junit.Assert;
import org.junit.Test;

public class TestNumberToLCD {

    @Test
    public void testSolutionWithOne() {
        String result = NumberToLCD.numbersToLcd(5);
        String expectedResult = " _ \n|_ \n _|";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionWithMultipleDigits() {
        String result = NumberToLCD.numbersToLcd(12);
        String expectedResult = "    _ \n  | _|\n  ||_ ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionWithNegativeNumber() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> NumberToLCD.numbersToLcd(-5));

        String expectedMsg = "Input number cannot be negative!";
        String actualMsg = exception.getMessage();

        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    public void testSolutionWithWidthHeight() {
        String result = NumberToLCD.numbersToLcd(2, 3, 2);
        String expectedResult = " ___ \n    |\n    |\n ___ \n|    \n|    \n ___ ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionWithMultipleDigitsWithWidthHeight() {
        String result = NumberToLCD.numbersToLcd(1234567890, 2, 2);
        String expectedResult =
                        "     __  __      __  __  __  __  __  __ \n" +
                        "   |   |   ||  ||   |      ||  ||  ||  |\n" +
                        "   |   |   ||  ||   |      ||  ||  ||  |\n" +
                        "     __  __  __  __  __      __  __     \n" +
                        "   ||      |   |   ||  |   ||  |   ||  |\n" +
                        "   ||      |   |   ||  |   ||  |   ||  |\n" +
                        "     __  __      __  __      __  __  __ ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionWithOneAndOne() {
        String result = NumberToLCD.numbersToLcd(3, 1, 1);
        String expectedResult =
                " _ \n" +
                        "  |\n" +
                        " _ \n" +
                        "  |\n" +
                        " _ ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionWithLargeHeight() {
        String result = NumberToLCD.numbersToLcd(5, 1, 8);
        String expectedResult = " _ \n|  \n|  \n|  \n|  \n|  \n|  \n|  \n|  \n" +
                " _ \n  |\n  |\n  |\n  |\n  |\n  |\n  |\n  |\n _ ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionWithLargeWidth() {
        String result = NumberToLCD.numbersToLcd(7, 10, 1);
        String expectedResult =
                " __________ \n" +
                        "           |\n" +
                        "            \n" +
                        "           |\n" +
                        "            ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionWithLargeSize() {
        String result = NumberToLCD.numbersToLcd(1, 10, 10);
        String expectedResult =
                "            \n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "            \n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "           |\n" +
                        "            ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSolutionZeroWidth() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class,
                () -> NumberToLCD.numbersToLcd(7, 0, 1));

        String expectedMsg = "Width and height have to be at least 1!";
        String actualMsg = exception.getMessage();

        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    public void testSolutionZeroHeight() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class,
                () -> NumberToLCD.numbersToLcd(3, 2, 0));

        String expectedMsg = "Width and height have to be at least 1!";
        String actualMsg = exception.getMessage();
        System.out.println(actualMsg);

        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSolutionNegativeWidth() {
        NumberToLCD.numbersToLcd(3, -2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSolutionNegativeHeight() {
        NumberToLCD.numbersToLcd(3, 4, -51);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSolutionWithSizeWithNegativeNumber() {
        NumberToLCD.numbersToLcd(-5, 1, 5);
    }

}
