// instructions
/*
 Ask user for width and height of an array.
Values must be numerical, 1 or greater.
If not display an error message and end the program.

Allocate two-dimensional array of integers of given width and height.

Generate random values from -99 to 99 (both inclusive) and assign to each cell of the array.

Display the array to the user in a user-friendly manner, where columns have all the same width (commas align). Example:

 -99,   2,  22
  15, -71,   5

Compute and display:
1) Sum of all numbers in the array
2) Sum of each of the row of the array
3) Sum of each of the column of the array
4) Standard deviation of all numbers in the array
5) Find pairs of numbers in the array whose sum is a prime number and display those pairs and their sum. Make sure you only show each pair *once*. You are allowed to use ArrayList here to hold the result.

Note: when printing out values from 5) you must display information in the following manner:

Prime sum 13 of pair value=7 at [1,3] and value=6 at [3,2]

Do not display this kind of duplicate:
Prime sum 13 of pair value=6 at [3,2] and value=7 at [1,3]

NOTE: You are NOT allowed to use advaced collections such as ArrayList unless otherwise specified.

Part B:
Move the code for each of the 5 operations above into a method.
Those methods must NOT print out anything but instead return a result.

For 5) you will need a better data structure to be able to return a list of results. Consider this:

class ValueAtRowCol {
  int value, row, col;
}

class Pair {
  ValueAtRowCol val1, val2;
}

ArrayList<Pair> findPrimePairs(int [] data) { ... }

Create Unit tests that appropriately test each of those methods.
 */

package day02twodimarrays;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class ValAtRowCol {

    public ValAtRowCol(int val, int row, int col) {
        this.val = val;
        this.row = row;
        this.col = col;
    }

    int val, row, col;

    @Override
    public String toString() {
        return String.format("[%d,%d:%d]", row, col, val);
    }

}

class Pair {

    public Pair(ValAtRowCol a, ValAtRowCol b) {
        this.a = a;
        this.b = b;
    }

    ValAtRowCol a, b;

    @Override
    public String toString() {
        return String.format("(%s,%s)", a, b);
    }

    @Override
    public boolean equals(Object o) {
        Pair other = (Pair) o;
        if (this.a.val == other.a.val && this.b.val == other.b.val) {
            return (this.a.col == other.a.col && this.a.row == other.a.row
                    && this.b.col == other.b.col && this.b.row == other.b.row);
        }
        if (this.a.val == other.b.val && this.b.val == other.a.val) {
            return (this.a.col == other.b.col && this.a.row == other.b.row
                    && this.b.col == other.a.col && this.b.row == other.a.row);
        }
        return false;
    }

}

public class Day02TwoDimArrays {

    static boolean isPrime(int num) {
        // by definition, 1 is not a prime number, 0 and negatives are not prime numbers either
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 1) Sum of all numbers in the array
    static int sumOfAll(int[][] data) {
        int sum = 0;
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                sum += data[row][col];
            }
        }
        return sum;
    }

    // 2) Sum of each of the row of the array
    static int[] sumOfEachRow(int[][] data) {
        // one result entry per row
        int[] rowSums = new int[data.length];
        for (int row = 0; row < data.length; row++) {
            int sum = 0;
            for (int col = 0; col < data[row].length; col++) {
                sum += data[row][col];
            }
            rowSums[row] = sum;
        }
        return rowSums;
    }

    // 3) Sum of each of the column of the array
    static int[] sumOfEachCol(int[][] data) {
        // one result per column
        int[] colSums = new int[data[0].length]; // this only works for retangular arrays, where all rows has same lenght
        for (int col = 0; col < data[0].length; col++) {
            int sum = 0;
            for (int row = 0; row < data[0].length; row++) {
                sum += data[row][col];
            }
            colSums[col] = sum;
        }
        return colSums;
    }

    //4) standard deviation
    static double computeStdDev(int[][] data) {
        // NOTE: this only works for rectangular arrays
        int valCount = (data.length * data[0].length);
        double avg = (double) sumOfAll(data) / valCount;
        int sumOfSquareDiff = 0;
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                int val = data[row][col];
                sumOfSquareDiff += (val - avg) * (val - avg);
            }
        }
        double stdDev = Math.sqrt(sumOfSquareDiff / valCount);
        return stdDev;
    }

    static void printPrimeNumberPairs(int[][] data) {
        // marker A
        for (int markerARow = 0; markerARow < data.length; markerARow++) {
            for (int markerACol = 0; markerACol < data[markerARow].length; markerACol++) {
                // marker B - starts at RowA, after the cell pointed to by marker 
                for (int markerBRow = markerARow; markerBRow < data.length; markerBRow++) {
                    int startMarkerBCol = (markerARow == markerBRow) ? markerACol + 1 : 0;
                    for (int markerBCol = startMarkerBCol; markerBCol < data[markerBRow].length; markerBCol++) {
                        // is the sum a prime number?
                        int sum = data[markerARow][markerACol] + data[markerBRow][markerBCol];
                        if (isPrime(sum)) {
                            System.out.printf("Prime sum %d at [%d][%d] val %d and [%d][%d] val %d\n",
                                    sum, markerARow, markerACol, data[markerARow][markerACol], markerBRow, markerBCol, data[markerBRow][markerBCol]);
                        }
                    }
                }
            }
        }
    }

    static ArrayList<Pair> getPrimeNumberPairs(int[][] data) {
        ArrayList<Pair> resultPairs = new ArrayList<>();
        // marker A
        for (int markerARow = 0; markerARow < data.length; markerARow++) {
            for (int markerACol = 0; markerACol < data[markerARow].length; markerACol++) {
                // marker B - starts at RowA, after the cell pointed to by marker 
                for (int markerBRow = markerARow; markerBRow < data.length; markerBRow++) {
                    int startMarkerBCol = (markerARow == markerBRow) ? markerACol + 1 : 0;
                    for (int markerBCol = startMarkerBCol; markerBCol < data[markerBRow].length; markerBCol++) {
                        // is the sum a prime number?
                        int sum = data[markerARow][markerACol] + data[markerBRow][markerBCol];
                        if (isPrime(sum)) {
                            ValAtRowCol a = new ValAtRowCol(data[markerARow][markerACol], markerARow, markerACol);
                            ValAtRowCol b = new ValAtRowCol(data[markerBRow][markerBCol], markerBRow, markerBCol);
                            Pair pair = new Pair(a, b);
                            resultPairs.add(pair);
                        }
                    }
                }
            }
        }
        return resultPairs;
    }

    public static void main(String[] args) {
        int width = 0;
        int height = 0;
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter width of the array: ");
            width = input.nextInt(); // add validation
            System.out.println("Enter height of the array: ");
            height = input.nextInt(); // add validation
            if (width < 1 || height < 1) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Error: size must be a number 1 or greater");
            System.exit(1);
        }

        // random numbers in array
        int[][] myArray = new int[height][width];
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                int random = (int) (Math.random() * 199 - 99);
                myArray[i][j] = random;
            }
        }

        // display array in user friendly manner
        for (int row = 0; row < myArray.length; row++) {
            for (int col = 0; col < myArray[row].length; col++) {
                int val = myArray[row][col];
                System.out.printf("%s%3d", col == 0 ? "" : ", ", val);
            }
            System.out.println();
        }

        // sum
        int sum = sumOfAll(myArray);
        System.out.println("Sum of all items: " + sum);
        System.out.println();

        // display sum of each row
        int[] sumRows = sumOfEachRow(myArray);
        System.out.println("Sums of rows: ");
        for (int row = 0; row < sumRows.length; row++) {
            System.out.printf("Row #%d has sum %d\n", row, sumRows[row]);
        }

        // display sum of each column
        System.out.println();
        int[] sumCols = sumOfEachCol(myArray);
        System.out.println("Sum of cols:");
        for (int i = 0; i < sumCols.length; i++) {
            System.out.printf("Col #%d has sum of %d\n", i, sumCols[i]);
        }

        // display standard deviation
        System.out.println();
        System.out.printf("Standard deviation is: %.3f\n", computeStdDev(myArray));

        //Find pairs of numbers in the array whose sum is a prime number and 
        //display those pairs and their sum. Make sure you only show each pair *once*. 
        //You are allowed to use ArrayList here to hold the result.
        printPrimeNumberPairs(myArray);
        // TODO: use getPrimeNumberPairs() and display the result
        System.out.println("=== Pairs using list of...");
        ArrayList<Pair> pairs = getPrimeNumberPairs(myArray);
        for (Pair p : pairs) {
            System.out.printf("Prime sum %d at [%d][%d] val %d and [%d][%d] val %d\n",
                    p.a.val + p.b.val,
                    p.a.row, p.a.col, p.a.val,
                    p.b.row, p.b.col, p.b.val);
        }
    }

}
