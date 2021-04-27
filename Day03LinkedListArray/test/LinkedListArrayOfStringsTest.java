/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import day03linkedlistarray.LinkedListArrayOfStrings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 15144
 */
public class LinkedListArrayOfStringsTest {
         @Test
    public void testAddToStringToArray() {
        System.out.println("add toString toArray");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Larry");
        instance.add("Marry");
        assertEquals(5, instance.getSize());
        assertEquals("[Jerry,Terry,Barry,Larry,Marry]", instance.toString());
        String[] result = instance.toArray();
        String[] expectedArray = {"Jerry", "Terry", "Barry", "Larry", "Marry"};
        assertArrayEquals(expectedArray, result);
    }

    @Test
    public void testAddGet() {
        System.out.println("add get");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Larry");
        instance.add("Marry");
        assertEquals("Jerry", instance.get(0));
        assertEquals("Larry", instance.get(3));
        assertEquals("Marry", instance.get(4));
    }

    @Test
    public void testAddDelete() {
        System.out.println("add get");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry"); // 0
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Marry"); // 3
        instance.add("Larry");
        instance.add("Zarry"); // 5
        instance.deleteByIndex(5);
        assertEquals("[Jerry,Terry,Barry,Marry,Larry]", instance.toString());
        instance.deleteByIndex(3);
        assertEquals("[Jerry,Terry,Barry,Larry]", instance.toString());
        instance.deleteByIndex(0);
        assertEquals("[Terry,Barry,Larry]", instance.toString());
    }
    
    @Test
    public void testAddInsert() {
        System.out.println("add insert");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.insertValueAtIndex("Barry",0);       
        assertEquals("[Barry,Jerry,Terry]", instance.toString());        
        instance.insertValueAtIndex("Marry",3); // really an add
        assertEquals("[Barry,Jerry,Terry,Marry]", instance.toString());        
        instance.insertValueAtIndex("Zarry",2);
        assertEquals("[Barry,Jerry,Zarry,Terry,Marry]", instance.toString());
    }
    }
