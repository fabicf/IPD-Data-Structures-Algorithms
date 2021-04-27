/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day02arraylistownimpl;

/**
 *
 * @author 15144
 */
public class Day02ArrayListOwnImpl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(3);
        instance.add(1);
        instance.add(2);
        instance.add(4);
        instance.insertValueAtIndex(11, 6); // too far, IOOBE
        System.out.println(instance);
    
    }
    
}
