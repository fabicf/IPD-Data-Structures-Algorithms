/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day03customhashmap;

/**
 *
 * @author 15144
 */
public class Day03CustomHashMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomHashMapStringString map = new CustomHashMapStringString();
        map.putValue("Jerry", "Blue");
        map.putValue("Jerry123", "Blue");
        map.putValue("JerryAAA", "Blue");
        map.putValue("JerryBBB", "Blue");
        map.putValue("Jerry2", "Blue");
        map.putValue("JerryNN", "Blue");
        map.putValue("Jerry", "White");
        map.putValue("Jerry", "Yellow");
        map.putValue("Jerry", "Red");
        map.printDebug();
        
        System.out.println("All keys: " + String.join(",", map.getAllKeys()));
        
        System.out.println(map);
    }
    
    
}
