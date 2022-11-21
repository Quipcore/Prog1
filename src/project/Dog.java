package project;
//Felix Lidö feli8145

import java.util.ArrayList;

public class Dog {

    private static final double DACHS_HUND_TAIL_LENGTH = 3.7;

    private ArrayList<String> dachshundLangArr = new ArrayList<>() {
        {
            add("tax");
            add("dachshund");
            add("mäyräkoira");
            add("teckel");
        }
    };

    private String name;
    private String breed;
    private int age;
    private int weight;
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
        if(dachshundLangArr.contains(breed.toLowerCase())){
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

