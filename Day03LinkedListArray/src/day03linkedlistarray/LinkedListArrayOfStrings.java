/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day03linkedlistarray;

/**
 *
 * @author 15144
 */
public class LinkedListArrayOfStrings {
     private class Container {
        Container next;
        String value;
    }
    
    private Container start, end;
    private int size;

    public void add(String value) {
        Container newCont = new Container();
        newCont.value = value;
        if (size == 0) { // special case: list is empty
            start = newCont;
            end = newCont;
            size = 1;
        } else {
            end.next = newCont;
            end = newCont;
            size++;
        }
    }
    
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == size-1) {  // optional: special case for the last item
            return end.value;
        }
        // regular search for the wanted item
        Container current = start;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
        
    }
    
    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) { // special case: removing the first item
            start = start.next; // go "over" the 0th item
            size--;
            return; 
        }
        // find the container just before the one we want to delete
        Container before = start;
        for (int i = 0; i < index-1; i++) {
            before = before.next;
        }
        // when removing the last item adjust end reference
        //if (before.next.next == null) {
        if (index == size - 1) {
            end = before;
        }
        before.next = before.next.next; // go "over" the item being deleted
        size--;        
    }
    
    public boolean deleteByValue(String value) { // delete first value found
        Container current = start;
        int index = 0;
        while (current.next != null){
            if (current.value == value){
              deleteByIndex(index);
              return true;
            }
            index++;
            current = current.next;
        }
        return false;
    }
    
    public void insertValueAtIndex(String value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == 0 || index == size) {
            // Why? this avoids having to handle two special cases in our code
            // insert to an empty list is an add
            // insert at the end is an add
            add(value);
            return;
        }
        if (index == 0) { // insert at the beginning of a non-empty list
            Container newCont = new Container();
            newCont.value = value;
            newCont.next = start;
            start = newCont;
            size++;
            return;
        }
        // find the container just before the position at which we want to insert
        Container before = start;
        for (int i = 0; i < index-1; i++) {
            before = before.next;
        }
        Container newCont = new Container();
        newCont.value = value;
        newCont.next = before.next;
        before.next = newCont;
        size++;
    }
    
    public int getSize() {
        return size;
    }

    @Override
    public String toString() { // comma-separated values list similar to: [5,8,11]
        StringBuilder sb = new StringBuilder("[");
        for (Container current = start; current != null; current = current.next) {
            sb.append(current == start ? "" :  ",");
            sb.append(current.value);
        }
        sb.append("]");
        return sb.toString();
    } 

    public String[] toArray() { // could be used for Unit testing
        String[] result = new String[size];
        Container current = start;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current = current.next;
        }
        return result;
    } 

}
