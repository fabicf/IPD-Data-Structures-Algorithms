/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import day02arraylistownimpl.CustomArrayOfInts;
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
public class CustomArrayOfIntsTest {
    
     @Test
    public void testAddAndGet() {
        System.out.println("add and get");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(3);
        instance.add(1);
        assertEquals(3, instance.size());
        instance.add(2);
        instance.add(4);
        assertEquals(1, instance.get(2));
        assertEquals(2, instance.get(3));
        assertEquals(3, instance.get(1));
        assertEquals(4, instance.get(4));
        assertEquals(5, instance.get(0));
        assertEquals("[5,3,1,2,4]", instance.toString());
    }
    
    @Test
    public void testInsertMany() {
        System.out.println("insert many");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(3);
        instance.add(1);
        instance.add(2);
        instance.add(4);
        instance.insertValueAtIndex(7, 2);
        assertEquals("[5,3,7,1,2,4]", instance.toString());
        instance.insertValueAtIndex(9, 6);
        assertEquals("[5,3,7,1,2,4,9]", instance.toString());
    }

    @Test
    public void testInsertIntoEmpty() {
        System.out.println("insert into empty");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.insertValueAtIndex(7, 0);
        assertEquals("[7]", instance.toString());        
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertException1() {
        System.out.println("insert exception, one too far");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(3);
        instance.add(1);
        instance.add(2);
        instance.add(4);
        instance.insertValueAtIndex(11, 6); // one too far, IOOBE
    }

    @Test
    public void testDeleteAtIndex() {
        System.out.println("delete at index middle");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(3);
        instance.add(1);
        instance.add(2);
        instance.add(4);
        instance.deleteByIndex(2);
        assertEquals("[5,3,2,4]", instance.toString());
    }

    @Test
    public void testDeleteAtIndexLast() {
        System.out.println("delete at index last");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(3);
        instance.add(1);
        instance.add(2);
        instance.add(4);
        instance.deleteByIndex(4);
        assertEquals("[5,3,1,2]", instance.toString());
    }

    @Test
    public void testDeleteByValue() {
        System.out.println("delete by value");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(3); // to be deleted
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        assertEquals(false, instance.deleteByValue(11));
        assertEquals(true, instance.deleteByValue(3));
        assertEquals("[5,1,2,3,4]", instance.toString());
    }

    @Test
    public void testGetSlice() {
        System.out.println("getslice");
        CustomArrayOfInts instance = new CustomArrayOfInts();
        instance.add(5);
        instance.add(-1);
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        int[] result = instance.getSlice(2, 3);
        assertArrayEquals(new int[]{1,2,3}, result);
        assertEquals(3, result.length);
    }
}
