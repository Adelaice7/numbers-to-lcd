package com.meunier;

import com.meunier.model.Segments;

import java.util.*;

public class NumberToLCD {

    private static int H = -1;
    private static int W = -1;

    /**
     * Converts input integer number to a String displaying the numbers in LCD format.
     * The default case is regular LCD-style display with no customization to the digits' width or height.
     *
     * @param number a positive Integer input number
     * @return the result String that displays the input number in LCD format.
     */
    public static String numbersToLcd(int number) {
        // setting width and height to default -1
        W = -1;
        H = -1;

        List<String[]> segments = arrangeDigitSegments(number);
        String[] segmentLines = writeToMultiLineSegments(segments);
        String response = convertToTextResult(segmentLines);
        System.out.println(response);
        return response;
    }

    public static String numbersToLcd(int number, int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Width and height have to be at least 1!");
        }

        // setting width and height parameters
        W = width;
        H = height;

        List<String[]> segments = arrangeDigitSegments(number);
        String[] segmentLines = writeToMultiLineSegments(segments);
        String response = convertToTextResult(segmentLines);

        System.out.println(response);
        return response;
    }

    /**
     * Search for the segments belonging to each digit in input number.
     * If number is negative, it throws an exception.
     * Converts input integer into string for iterating the digits.
     *
     * Sets the Segments map to basic or custom-sized numbers.
     *
     * It iterates the digits of the input number and finds the segments from the segments map for each digit.
     *
     * @param number the input number to convert
     * @return a List of String[] arrays containing the digit segments in LCD format for each digit
     */
    private static List<String[]> arrangeDigitSegments(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Input number cannot be negative!");
        }

        String numString = String.valueOf(number);

        List<String[]> segmentsListForNums = new ArrayList<>();

        setSegmentsMap();

        for (int i = 0; i < numString.length(); i++) {
            int currentNum = numString.charAt(i) - '0';
            String[] segmentsForCurrentNum = getSegmentForNumber(currentNum);
            segmentsListForNums.add(segmentsForCurrentNum);
        }
        return segmentsListForNums;
    }

    /**
     * Sets the Segments map to generating and storing basic or custom-sized digits.
     */
    private static void setSegmentsMap() {
        if (H > -1 && W > -1) {
            Segments.setSizedSegments(W, H);
        } else {
            Segments.setBasicMap();
        }
    }

    /**
     * Find proper segment for a given number.
     * It searches the Segments map for the proper segments for each given input digit.
     *
     * @param number a single digit number to find in the Segments map
     * @return a String[] array containing the segments for a number
     */
    private static String[] getSegmentForNumber(int number) {
        String[] segmentsForNumber = Segments.getNumSegments().get(number);

        if (segmentsForNumber == null) {
            throw new NoSuchElementException("Could not find digit " + number);
        }

        return segmentsForNumber;
    }

    /**
     * Converts the existing segments list into a multi-line segments String[] array.
     * It takes the first element of the segments list for each digit,
     * and then copies the rest into it, column by column.
     *
     * @param list The list of String[] arrays containing the segments for each digit.
     *             List cannot be null because number is checked earlier, and if number is valid,
     *             the list will contain some segment elements.
     * @return a String[] array containing the total multi-line segments list.
     */
    private static String[] writeToMultiLineSegments(List<String[]> list) {
        String[] segmentLines = list.get(0);

        for (int r = 1; r < list.size(); r++) {
            for (int c = 0; c < list.get(r).length; c++) {
                segmentLines[c] += list.get(r)[c];
            }
        }

        return segmentLines;
    }

    /**
     * Takes the String[] array containing the multi-line segments
     * and joins them into a single line for final display.
     *
     * @param segmentLines The String[] array containing the segment lines for all the digits in the input number.
     * @return a String that is the final result for displaying the total sequence of numbers in LCD format.
     */
    private static String convertToTextResult(String[] segmentLines) {
        String delim = "\n";

        StringBuilder sb = new StringBuilder();
        for (String str : segmentLines) {
            if (sb.length() > 0) {
                sb.append(delim);
            }
            sb.append(str);
        }

        return sb.toString();
    }

}
