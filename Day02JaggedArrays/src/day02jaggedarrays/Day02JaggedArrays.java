//instructions
/*
//Write a program that will compute and display the following information for a jagged array:
//
//0. Print out the original array, one row per line
//1. Sum of all items
//2. Sum of each row
//3. Sum of each column (tricky)
//4. Cross-sum of surrounding elements (similar to a previous task) and create a new array with sum values.
//
//Here's an example array you can use in your program:
//
//int[][] jaggedArray2d = {
//{  1,  2 },
//{  3,  4,  5,  6,  7 },
//{ 11, 12, 13, 14, 15, 16},
//{  8,  9, 10} };
//
//For #3 you will probably want to measure the max length of each row to know how many columns you have.
//Result for the above jagged array would be:
//[ 23, 27, 28, 20, 22, 16]
//
//Sum values resulting array with exactly the same dimensions as the original array:
//[ 6,  7]
//[19, 26, 28, 32, 28 ]
//[ ... ]
 */

package day02jaggedarrays;


public class Day02JaggedArrays {

    public static void printArray(int[][] dataArray) {
        for (int row = 0; row < dataArray.length; row++) {
            for (int col = 0; col < dataArray[row].length; col++) {
                int val = dataArray[row][col];
                System.out.printf("%s%3d", col == 0 ? "" : ", ", val);
            }
            System.out.println();
        }
    }

    public static int getSumOfAll(int[][] dataArray) {
        int sum = 0;
        for (int row = 0; row < dataArray.length; row++) {
            for (int col = 0; col < dataArray[row].length; col++) {
                int val = dataArray[row][col];
                sum += val;
            }
        }
        return sum;
    }

    public static int[] getSumOfEachRow(int[][] dataArray) {
        int[] result = new int[dataArray.length];
        for (int row = 0; row < dataArray.length; row++) {
            int sum = 0;
            for (int col = 0; col < dataArray[row].length; col++) {
                int val = dataArray[row][col];
                sum += val;
            }
            result[row] = sum;
        }
        return result;
    }

    // Note: this must work for jagged array as well
    public static int[] getSumOfEachCol(int[][] dataArray) {
        // find the max row length
        int maxCol = 0;
        for (int row = 0; row < dataArray.length; row++) {
            maxCol = dataArray[row].length > maxCol ? dataArray[row].length : maxCol;
        }
        //
        int[] result = new int[maxCol];
        for (int col = 0; col < maxCol; col++) {
            int sum = 0;
            for (int row = 0; row < dataArray.length; row++) {
                // only add to sum if the row/col actually exists
                if (col < dataArray[row].length) {
                    sum += dataArray[row][col];
                }
            }
            result[col] = sum;
        }
        return result;
    }

    // Note: this must work for jagged array as well
    public static int[] getSumOfEachColWithException(int[][] dataArray) {
        // find the max row length
        int maxCol = 0;
        for (int row = 0; row < dataArray.length; row++) {
            maxCol = dataArray[row].length > maxCol ? dataArray[row].length : maxCol;
        }
        //
        int[] result = new int[maxCol];
        for (int col = 0; col < maxCol; col++) {
            int sum = 0;
            for (int row = 0; row < dataArray.length; row++) {
                try {
                    // only add to sum if the row/col actually exists
                    sum += dataArray[row][col]; // this causes exceptions
                } catch (ArrayIndexOutOfBoundsException ex) { // ignore
                }
            }
            result[col] = sum;
        }
        return result;
    }

    public static int[] getSumOfEachColAndreysVersion(int[][] data) {
        // find the max row length (how many columns do I have, at most)
        int maxSize = 0;
        for (int row = 0; row < data.length; row++) {
            maxSize = data[row].length > maxSize ? data[row].length : maxSize;
        }
        int[] sumByCol = new int[maxSize]; // Storage for sum by cols
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                int val = data[row][col];
                sumByCol[col] += val;
            }
        }
        return sumByCol;
    }

    static int getIfExists(int[][] data, int row, int col) {
        // If exists, return the element, otherwise return 0
        if (data.length <= row || row < 0) {
            return 0;
        }
        if (data[row].length <= col || col < 0) {
            return 0;
        }
        return data[row][col];
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

    public static int[][] getCrossSumArray(int[][] data) {
        // allocate an array of the same dimensions remembering it may be a jagged array
        int[][] result = new int[data.length][]; // allocate array of references to rows
        for (int i = 0; i < data.length; i++) {
            int[] oneRow = new int[data[i].length];
            result[i] = oneRow;
        }
        //
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                int sum = sumOfCross(data, row, col);
                result[row][col] = sum;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] jaggedArray2d = {
            {1, 2},
            {3, 4, 5, 6, 7},
            {11, 12, 13, 14, 15, 16},
            {8, 9, 10}};
        printArray(jaggedArray2d);
        System.out.println("Sum of all items: " + getSumOfAll(jaggedArray2d));
        //
        int[] sumRows = getSumOfEachRow(jaggedArray2d);
        System.out.print("Sum of each row: ");
        for (int i = 0; i < sumRows.length; i++) {
            System.out.printf("%s%d", i == 0 ? "" : ",", sumRows[i]);
        }
        System.out.println("");
        //
        int[] sumCols = getSumOfEachColAndreysVersion(jaggedArray2d);
        System.out.print("Sum of each column: ");
        for (int i = 0; i < sumCols.length; i++) {
            System.out.printf("%s%d", i == 0 ? "" : ",", sumCols[i]);
        }
        System.out.println("");
        //
        System.out.println("Sum of cross values array:");
        int [][] crossSums = getCrossSumArray(jaggedArray2d);
        printArray(crossSums);
    }
    
}
