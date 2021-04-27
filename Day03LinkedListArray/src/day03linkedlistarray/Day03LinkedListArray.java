/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day03linkedlistarray;

class Person {

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    String name;
    int age;

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", age=" + age + '}';
    }
}
public class Day03LinkedListArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedListArray<Person> llap = new LinkedListArray<>();
        llap.add(new Person("Jerry", 33));
        llap.add(new Person("Terry", 33));
        llap.add(new Person("Larry", 33));
        System.out.println(llap);
    
    }
    
}
