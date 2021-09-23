package com.meunier.model;

import java.util.*;

public class Segments {

    private static final String EMPTY = "   ";
    private static final String BOTH = "| |";
    private static final String BOTTOM = "|_|";
    private static final String RIGHT = "  |";
    private static final String MIDRIGHT = " _|";
    private static final String MIDLEFT = "|_ ";
    private static final String MID = " _ ";

    private static final String SINGLE_HORIZONTAL = "_";
    private static final String SINGLE_VERTICAL = "|";
    private static final String SINGLE_EMPTY = " ";

    private static Map<Integer, String[]> NUM_SEGMENTS;

    static {
        setBasicMap();
    }

    public static Map<Integer, String[]> getNumSegments() {
        return NUM_SEGMENTS;
    }

    /**
     * Sets basic segments map values for regular 3-line high LED numbers.
     */
    public static void setBasicMap() {
        NUM_SEGMENTS = new HashMap<>();

        NUM_SEGMENTS.put(1, new String[]{EMPTY, RIGHT, RIGHT});
        NUM_SEGMENTS.put(2, new String[]{MID, MIDRIGHT, MIDLEFT});
        NUM_SEGMENTS.put(3, new String[]{MID, RIGHT, MIDRIGHT});
        NUM_SEGMENTS.put(4, new String[]{EMPTY, BOTH, RIGHT});
        NUM_SEGMENTS.put(5, new String[]{MID, MIDLEFT, MIDRIGHT});
        NUM_SEGMENTS.put(6, new String[]{MID, MIDLEFT, BOTTOM});
        NUM_SEGMENTS.put(7, new String[]{MID, RIGHT, RIGHT});
        NUM_SEGMENTS.put(8, new String[]{MID, BOTTOM, BOTTOM});
        NUM_SEGMENTS.put(9, new String[]{MID, BOTTOM, MIDRIGHT});
        NUM_SEGMENTS.put(0, new String[]{MID, BOTH, BOTTOM});
    }

    /**
     * Change size of segments according to set height or width.
     * Generates the segments map for each number.
     * Each underline character is the equivalent of width=1,
     * and each vertical bar character per section is the equivalent of height=1;
     * <p>
     * A section is one vertical bar on regular LCD numbers.
     * A custom sized LCD-style number consists of 5 parts:
     * top line, top section, middle line, bottom section and bottom line.
     *
     * @param width  the width of the digit, marking a single underscore character
     * @param height the height of the digit, marking a single vertical bar character
     */
    public static void setSizedSegments(int width, int height) {
        String singleLine = generateLine(width);
        String emptyLine = generateEmptyLine(width);

        // singleLine.length() is required for width as a line contains 2 extra spaces for cols!
        List<String> rightCols = generateColumns(height, singleLine.length(), true);
        List<String> leftCols = generateColumns(height, singleLine.length(), false);
        List<String> doubleCols = generateDoubleColumns(height, width);

        int number = 0;
        while (number < 10) {
            generateSegmentsForNumber(number, singleLine, emptyLine, rightCols, leftCols, doubleCols);
            number++;
        }
    }

    /**
     * Fill segments map with proper segments for each number.
     *
     * @param singleLine a single line of underline characters, width wide
     * @param emptyLine  a single empty line with space characters, width wide
     * @param rightCols  list of right columns, height high, width spaces before
     * @param leftCols   list of left columns, height high, width spaces after
     * @param doubleCols list of double columns, width spaces between
     * @param number     one digit input number
     */
    private static void generateSegmentsForNumber(int number, String singleLine, String emptyLine,
                                                  List<String> rightCols, List<String> leftCols,
                                                  List<String> doubleCols) {
        switch (number) {
            case 1:
                NUM_SEGMENTS.put(1, getSegments(emptyLine, rightCols, emptyLine, rightCols, emptyLine));
                break;
            case 2:
                NUM_SEGMENTS.put(2, getSegments(singleLine, rightCols, singleLine, leftCols, singleLine));
                break;
            case 3:
                NUM_SEGMENTS.put(3, getSegments(singleLine, rightCols, singleLine, rightCols, singleLine));
                break;
            case 4:
                NUM_SEGMENTS.put(4, getSegments(emptyLine, doubleCols, singleLine, rightCols, emptyLine));
                break;
            case 5:
                NUM_SEGMENTS.put(5, getSegments(singleLine, leftCols, singleLine, rightCols, singleLine));
                break;
            case 6:
                NUM_SEGMENTS.put(6, getSegments(singleLine, leftCols, singleLine, doubleCols, singleLine));
                break;
            case 7:
                NUM_SEGMENTS.put(7, getSegments(singleLine, rightCols, emptyLine, rightCols, emptyLine));
                break;
            case 8:
                NUM_SEGMENTS.put(8, getSegments(singleLine, doubleCols, singleLine, doubleCols, singleLine));
                break;
            case 9:
                NUM_SEGMENTS.put(9, getSegments(singleLine, doubleCols, singleLine, rightCols, singleLine));
                break;
            case 0:
                NUM_SEGMENTS.put(0, getSegments(singleLine, doubleCols, emptyLine, doubleCols, singleLine));
                break;
        }
    }

    /**
     * Generates segments for a single-digit number.
     *
     * @param firstLine  the first line of a number (underlines or empty line)
     * @param upperCols  the top section of columns (height tall) of  a number
     * @param midLine    the middle line of a number (underlines, or empty line)
     * @param lowerCols  the bottom section of columns (height tall) of a number
     * @param bottomLine the bottom line of a number (underlines or empty line)
     * @return the String[] array of segments for a digit.
     */
    private static String[] getSegments(String firstLine, List<String> upperCols,
                                        String midLine, List<String> lowerCols, String bottomLine) {
        List<String> arrayList;

        arrayList = new ArrayList<>();
        arrayList.add(firstLine);
        arrayList.addAll(upperCols);
        arrayList.add(midLine);
        arrayList.addAll(lowerCols);
        arrayList.add(bottomLine);

        return convertListToArray(arrayList);
    }

    /**
     * Converts ArrayList<String> to String[] array.
     *
     * @param arrayList the List<String> array list
     * @return the String[] array
     */
    private static String[] convertListToArray(List<String> arrayList) {
        String[] array = new String[arrayList.size()];
        array = arrayList.toArray(array);
        return array;
    }

    /**
     * Generates an empty line filled with width wide spaces.
     * Empty lines are required for sizing digits correctly.
     *
     * @param width the width of the digit
     * @return a single empty line with spaces
     */
    private static String generateEmptyLine(int width) {
        return SINGLE_EMPTY +
                SINGLE_EMPTY.repeat(Math.max(0, width)) +
                SINGLE_EMPTY;
    }

    /**
     * Generates a single line that is width wide.
     *
     * @param width the width of the digit (current line)
     * @return the single width wide line
     */
    private static String generateLine(int width) {
        return SINGLE_EMPTY +
                SINGLE_HORIZONTAL.repeat(Math.max(0, width)) +
                SINGLE_EMPTY;
    }

    /**
     * Generate a list of double column lines according to the required height.
     *
     * @param height the height of the digit
     * @param width  the width of the digit
     * @return the List<String> of double column lines
     */
    private static List<String> generateDoubleColumns(int height, int width) {
        List<String> segsList = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            String colLine = generateDoubleColumnLine(width);
            segsList.add(colLine);
        }

        return segsList;
    }

    /**
     * Generates a single line with columns on both side, and width spaces in between.
     *
     * @param width the width required for the spaces in between columns
     * @return the single line of double columns.
     */
    private static String generateDoubleColumnLine(int width) {
        return SINGLE_VERTICAL +
                SINGLE_EMPTY.repeat(Math.max(0, width)) +
                SINGLE_VERTICAL;
    }

    /**
     * Generate a list of column lines according to the required height.
     *
     * @param height  the height of the digit
     * @param width   the width of the digit
     * @param isRight right or left sided column, rest are filled with empty spaces
     * @return the List<String> of single column lines
     */
    private static List<String> generateColumns(int height, int width, boolean isRight) {
        List<String> segsList = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            String colLine = generateColumnLine(width, isRight);
            segsList.add(colLine);
        }

        return segsList;
    }

    /**
     * Generates a right or left column line that is width wide
     *
     * @param width   how wide the column should be
     * @param isRight right or left sided column, rest are filled with empty spaces
     * @return the String containing a single column line
     */
    private static String generateColumnLine(int width, boolean isRight) {
        StringBuilder sb = new StringBuilder();

        if (isRight) {
            sb.append(SINGLE_EMPTY.repeat(Math.max(0, width - 1)));
            sb.append(SINGLE_VERTICAL);
        } else {
            sb.append(SINGLE_VERTICAL);
            sb.append(SINGLE_EMPTY.repeat(Math.max(0, width - 1)));
        }

        return sb.toString();
    }
}
