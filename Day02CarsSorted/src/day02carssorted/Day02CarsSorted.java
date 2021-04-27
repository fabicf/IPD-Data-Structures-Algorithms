// instructions
/* instructions
 Part 1: setup

Define class Car as follows, you will add a suitable constructor and toString().

class Car {
	String makeModel;
	double engineSizeL;
	int prodYear;
}

To the main class add:

static ArrayList<Car> parking = new ArrayList<>();

Instantiante 5 or more Cars and add them to the list.

Part 2: sorting

Using knowledge from the tutorials, do the following in the main method:

1. Print out cars, one per line (implement toString that displays all fields)
2. Sort cars by makeModel using Comparable<Car> interaface or lamba and display the list
3. Sort cars by engineSizeL (using Comparator<Car> or lamba) and display the list 
4. Sort cars by prodYear (using Comparator<Car> or lamba) and display the list
5. Sort cars by prodYear then makeModel (using Comparator<Car> or lamba) and if prodYear is the same then by makeModel (you need to create a few entries with the same prodYear to test this)

Part 3: reading from file

In the main directory of your project create file "cars.txt" with content similar to:

Audi A8;2.2;2012
Toyota Corolla;1.8;2020
(add 8 more lines like that - fields semicolon-separated)

Instead of instantiating data in your code you will create method

void readDataFromFile() { ... }

That method will be called at the beginning of the main() method.
It will load data from file, instantiate Car objects with values provided in each line.

Make sure you handle File I/O and parsing exceptions in your code.

 */

package day02carssorted;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Car {
	String makeModel;
	double engineSizeL;
	int prodYear;

    public Car(String makeModel, double engineSizeL, int prodYear) {
        this.makeModel = makeModel;
        this.engineSizeL = engineSizeL;
        this.prodYear = prodYear;
    }

    @Override
    public String toString() {
        return "make Model: " + makeModel + ", engineSizeL: " + engineSizeL + ", produced in year: " + prodYear + ".";
    }
    
     public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public double getEngineSizeL() {
        return engineSizeL;
    }

    public void setEngineSizeL(double engineSizeL) {
        this.engineSizeL = engineSizeL;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }
   
                
}

public class Day02CarsSorted {

    static ArrayList<Car> parking = new ArrayList<>();
    
   static void readDataFromFile() {
        try (Scanner fileInput = new Scanner(new File("cars.txt"))) {
            while (fileInput.hasNextLine()) {
                try {
                    String line = fileInput.nextLine();
                    String[] data = line.split(";");
                    if (data.length != 3) {
                        System.out.println("Error in line, skipping");
                        continue;
                    }
                    String makeModel = data[0];
                    double engSize = Double.parseDouble(data[1]);
                    int yop = Integer.parseInt(data[2]);
                    parking.add(new Car(makeModel, engSize, yop));
                } catch (NumberFormatException ex) {
                    System.out.println("Error in line, skipping: " + ex);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        
     readDataFromFile();
        System.out.println("==== Parking:");
        for (Car car : parking) {
            System.out.println(car);
        }
        // sort by makeModel
        parking.sort((Car c1, Car c2) -> c1.getMakeModel().compareTo(c2.getMakeModel()));
        System.out.println("==== Parking sorted by make model:");
        for (Car car : parking) {
            System.out.println(car);
        }
        // sort by engine size
        parking.sort((Car c1, Car c2) -> Double.compare(c1.getEngineSizeL(), c2.getEngineSizeL()));
        System.out.println("==== Parking sorted by engine size:");
        for (Car car : parking) {
            System.out.println(car);
        }
        // sort by yop
        parking.sort((Car c1, Car c2) -> c2.getProdYear() - c1.getProdYear());
        System.out.println("==== Parking sorted by prod year:");
        for (Car car : parking) {
            System.out.println(car);
        }
        // sort by year then makeModel
        Comparator<Car> compProdYearMakeModel = Comparator.comparing(Car::getProdYear).thenComparing(Car::getMakeModel); //.reversed();
        parking.sort(compProdYearMakeModel);
        System.out.println("==== Parking sorted by prod year then make model:");
        for (Car car : parking) {
            System.out.println(car);
        }

    }

}
