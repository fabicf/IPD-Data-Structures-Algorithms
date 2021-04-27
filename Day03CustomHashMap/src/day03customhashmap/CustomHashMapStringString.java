/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day03customhashmap;

import java.util.Arrays;

class KeyNotFoundException extends RuntimeException { }
// HashMap, key is String, value is String
public class CustomHashMapStringString {
     private class Container {

        Container next;
        String key;
        String value;
    }

    // size must be a prime number always
    private Container[] hashTable = new Container[5];

    private int totalItems;

    private int computeHashValue(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash <<= 1;  // same as: hash *= 2, adding xor may be better
            char c = key.charAt(i);
            //hash += c;
            hash = hash ^ c;
        }
        return hash;
    }

    String getValue(String key) {
        int hash = computeHashValue(key);
        int index = hash % hashTable.length;
        for (Container current = hashTable[index]; current != null; current = current.next) {
            if (current.key.equals(key)) {
                return current.value;
            }
        }
        throw new KeyNotFoundException();
    }

    void putValue(String key, String value) {
        int hash = computeHashValue(key);
        int index = hash % hashTable.length;
        // if key already exists then update value
        Container current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // update an existing entry
                return;
            }
            current = current.next; // got to next container
        }
        // we only reach this code if key was not found
        Container newCont = new Container();
        newCont.key = key;
        newCont.value = value;
        // insert at the beginning of hash table entry
        newCont.next = hashTable[index];
        hashTable[index] = newCont;
        totalItems++;
    }

    // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length

    boolean hasKey(String key) {
        throw new UnsupportedOperationException("getValue not implemented yet");
    }

    void deleteByKey(String key) {
        int hash = computeHashValue(key);
        int index = hash % hashTable.length;
        //
        Container previous = null;
        Container current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                break;
            }
            previous = current;
            current = current.next;
        }
        if (current == null) { // key not found
            throw new KeyNotFoundException();
        }
        if (previous == null) { // removing the first item
            hashTable[index] = current.next;
        } else { // removing one of the latter items
            previous.next = current.next;
        }
        totalItems--;
    }

    public String[] getAllKeys() {
        String [] result = new String[totalItems];
        int nextResultIndex = 0;
        for (int i = 0; i < hashTable.length; i++) {
            for (Container current = hashTable[i]; current != null; current = current.next) {
                result[nextResultIndex] = current.key;
                nextResultIndex++;
            }
        }
        return result;
    }

    int getSize() {
        return totalItems;
    }

    public void printDebug() { // print hashTable content
        for (int i = 0; i < hashTable.length; i++) {
            System.out.printf("Entry %d:\n", i);
            for (Container current = hashTable[i]; current != null; current = current.next) {
                System.out.printf("- Key: %s, Value: %s\n", current.key, current.value);
            }
        }
    } 

    @Override
    public String toString() {
        // comma-separated values->key pair list
        // to be able to use this as validation in Unit tests keys must be sorted
        // e.g. [ Key1 => Val1, Key2 => Val2, ... ]
        String [] keysArray = getAllKeys();
        Arrays.sort(keysArray);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < keysArray.length; i++) {
            String key = keysArray[i];
            String val = getValue(key);
            sb.append(i == 0 ? "" : ",");
            sb.append(key).append("->").append(val);
        }
        sb.append("]");
        return sb.toString();
    } 

}
