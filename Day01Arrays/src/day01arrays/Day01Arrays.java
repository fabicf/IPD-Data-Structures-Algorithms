/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day01arrays;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author 15144
 */
public class Day01Arrays {

    static boolean isPrime(int num){
        if (num == 1) return false; // 1 is not prime
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0){
                return false; // if is divisible by any number besides 1 and itself, is not prime
            }
        }
        return true; // if does not apply the for loop condition then is prime
    }
         
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int arraySize = 0;
        try{
            System.out.println("Please enter the size of the array");
            arraySize = input.nextInt();
            if (arraySize < 1){
                throw new InputMismatchException();
            } 
        }catch (InputMismatchException ex){
            System.out.println("Array size should be 1 or greater");
            System.exit(1);
        }
        
        // put random numbers in the array
        int[] myArray = new int[arraySize];
        for (int i = 0; i <myArray.length; i++) {
           int random = (int)(Math.random()*100 + 1);
           myArray[i] = random;
        }
        
        //display array
        for (int i = 0; i <myArray.length; i++) {
            System.out.printf("%s%d", i == 0 ? "" : ", ", myArray[i]);
        }
        System.out.println();
        
        // prime numbers
        System.out.println("prime numbers");
        boolean anyPrimeFound = false;
        for (int i = 0; i <myArray.length; i++) {
            int val = myArray[i];
            if (isPrime(val)){ // execute only if val is prime
                System.out.printf("%s%d", false == anyPrimeFound ? "" : ", ", val);
                anyPrimeFound = true;
            }
        }
        System.out.println();
        
    }    
}
