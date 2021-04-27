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
public class CustomArrayOfInts {
    
    private int[] data = new int[1]; // only grows by doubling size, never shrinks
    private int size = 0; // how many items do you really have

    public int size() {
        return size;
    }

    private void ensureAvailableStorageForOneMore() {
        // do we need to increase the size of data storage?
        if (size == data.length) { // no more space left
            int [] newData = new int[data.length*2]; // double the current size
            for (int i = 0; i < data.length; i++) { // copy values to new data storage
                newData[i] = data[i];
            }
            
            data = newData;
        }        
    }
    
    public void add(int value) {
        if (data.length == size) { // array full - must grow it first
            ensureAvailableStorageForOneMore();
        }
        // put value into data storage
        data[size] = value;
        size++;
    }

    
    public void deleteByIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index + 1; i < size; i++) { // forward, i is source index
            data[i-1] = data[i];
        }
        size--;
    }
    
    public boolean deleteByValue(int value) {
        // delete first value matching, true if value found, false otherwise
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                deleteByIndex(i);
                return true;
            }
        }
        return false;
    }
    
    public void insertValueAtIndex(int value, int index) {
        if (data.length == size) { // array full - must grow it first
            ensureAvailableStorageForOneMore();
        }
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // move items from index until size to the right, in reverse
        for (int i = size-1; i >= index; i--) { // loop MUST run in reverse
            data[i+1] = data[i]; // i is source index
        }
        // put the new value in
        data[index] = value;
        size++;
    }

    public void clear() {
        size = 0;
    }

    public int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("No such element");
        }
        return data[index];
    } // may throw IndexOutOfBoundsException

    public int[] getSlice(int startIdx, int length) {  // may throw IndexOutOfBoundsException
        // careful with 'off by one errors'
        if (startIdx < 0 || length < 0 || startIdx + length > size) {
            throw new IndexOutOfBoundsException("No such element");
        }
        int []result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = data[i+startIdx];
            //result[i-startIdx] = data[i];
        }
        return result;
    }

    @Override
    public String toString() {// returns String similar to: [3,5,6,-23]
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(i == 0 ? "" :  ",");
            result.append(data[i]);
        }
        result.append("]");
        return result.toString();
    }
    
    public int[] toArray() {
        int [] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }
}
