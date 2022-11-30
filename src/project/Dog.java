package project;
//Felix Lidö feli8145

import java.util.ArrayList;
import java.util.Arrays;

public class Dog {

    private static final double DACHS_HUND_TAIL_LENGTH = 3.7;

    private final ArrayList<String> dachshundLangList =
            new ArrayList<>(Arrays.asList("tax","dachs","mäyräkoira","teckel"));

    private final String name;
    private final String breed;
    private int age;
    private final int weight;
    private double tailLength;


    public Dog(String name, String breed, int age, int weight){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;

        increaseTail();

    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public void increaseAge(){
        this.age++;
        increaseTail();
    }

    private void increaseTail(){
        if(dachshundLangList.contains(breed.toLowerCase())){
            this.tailLength = DACHS_HUND_TAIL_LENGTH;

        }else{
            this.tailLength = age*weight/10d;
        }
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        return tailLength;
    }

    public String toString(){
        return name + ", " + breed + ", " + age + " years, " + weight + " kilo, " + tailLength + " cm tail";
    }
}

