//instructions
/*
 In your program define method as below:

    static int getIfExists(int[][] data, int row, int col) {
	     // If exists, return the element, otherwise return 0
    }

    static int sumOfCross(int[][] data, int row, int col) {
	    // return sum of the element at row/col
        // plus (if they exist) element above, below, to the left and right of it
    }
    
	// later: static void print2D(int[][] data) { ... }
	
	// later: static int[][] duplicateArray2D(int[][] orig2d) { ... }
	
    public static void main(String[] args) {
        int[][] data2D = {
            {1, 3, 6, 8},
            {7, 1, 2, 3},
            {8, 3, 2, 1},
            {1, 7, 1, 9},
        };

}
		
PART A:
Using sumOfCross() method write a search that will find
which element at row/col has the smallest sum of itself
and elements surrounding it.
        
PART B: Create an integer array data2Dsums of identical size to data2D where
each element of it is the cross-sum of the element in the original array.
then print out the original, and print out the new array
 */

package day01arraysearch;

/**
 *
 * @author 15144
 */
public class Day01ArraySearch {

    static int getIfExists(int[][] data, int row, int col) {
        try { // If exists, return the element, otherwise return 0
            if (data.length <= row || row < 0) {
                return 0;
            }
            if (data[row].length <= col || col < 0) {
                return 0;
            }
            return data[row][col]; //1
        } catch (ArrayIndexOutOfBoundsException ex) {
            return 0;
        }
    }

    static int sumOfCross(int[][] data, int row, int col) {
        // return sum of the element at row/col
        // plus (if they exist) element above, below, to the left and right of it
        int middle = getIfExists(data, row, col); //1
        int left = getIfExists(data, row - 1, col); //0
        int right = getIfExists(data, row + 1, col);//3
        int bottom = getIfExists(data, row, col - 1);//0
        int top = getIfExists(data, row, col + 1);//7

        return middle + left + right + bottom + top;
    }

    static int[][] duplicateEmptyArray2D(int[][] orig2d) {
        int[][] result = new int[orig2d.length][];
        for (int row = 0; row < orig2d.length; row++) {
            int rowLength = orig2d[row].length;
            result[row] = new int[rowLength];
        }
        return result;
    }

    static void print2D(int[][] data) {
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                System.out.printf("%s%d", col == 0 ? "" : ",", data[row][col]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] data2D = {
            {1, 3, 6, 8},
            {7, 1, 2, 3},
            {8, 3, 2, 1},
            {1, 7, 1, 9},};

        // Part A - Finding the smallest sum  of value
        int min = sumOfCross(data2D, 0, 0); // compare with the first value
        //int min = 9000; // or compare with a big big value
        int minRow = 0;
        int minCol = 0;

        for (int row = 0; row < data2D.length; row++) {
            for (int col = 0; col < data2D[row].length; col++) {
                int sum = sumOfCross(data2D, row, col); // sum of each cross value here = int sum
                if (sum < min) { // comparing if the int sum found is less then the value of min (9000 for the first round)
                    min = sum; // if the sum found is less, the new min value is = sum.
                    minRow = row;
                    minCol = col;
                }
            }
        }
        System.out.printf("The smallest cross sum is %d, found for element row %d, col %d\n",
                min, minRow, minCol);

        // Finding the biggest sum of value
        System.out.println();
        int max = sumOfCross(data2D, 0, 0); //comparing with the first value
        //int max = 0; // comparing with a small small value;
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row < data2D.length; row++) {
            for (int col = 0; col < data2D[row].length; col++) {
                int sum = sumOfCross(data2D, row, col);
                if (sum > max) {
                    max = sum;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }
        System.out.printf("The biggest cross sum is %d, found for element row %d, col %d\n",
                max, maxRow, maxCol);

        // Part B - print the new array 2d
        int[][] cross2D = duplicateEmptyArray2D(data2D);

        for (int row = 0; row < data2D.length; row++) {
            for (int col = 0; col < data2D[row].length; col++) {
                cross2D[row][col] = sumOfCross(data2D, row, col);
            }
        }
        //
        print2D(cross2D);

    }

}
