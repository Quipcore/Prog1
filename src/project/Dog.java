package project;// Felix Lidö feli8145

import java.util.ArrayList;
import java.util.Arrays;

public class Dog {

    private static final double DACHSHUND_TAIL_LENGTH = 3.7;

    private static final ArrayList<String> DACHSHUND_LANG_LIST =
            new ArrayList<>(Arrays.asList("tax", "dachshund", "mäyräkoira", "teckel"));

    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;

    private Owner owner;
    private boolean hasOwner;


    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        hasOwner = false;
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

    public void increaseAge() {
        this.age++;
        increaseTail();
    }

    private void increaseTail() {
        if (DACHSHUND_LANG_LIST.contains(breed.toLowerCase())) {
            this.tailLength = DACHSHUND_TAIL_LENGTH;

        } else {
            this.tailLength = age * weight / 10d;
        }
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        return tailLength;
    }

    public String toString() {
        String str = name + ", "
                + breed + ", "
                + age + " years, "
                + weight + " kilo, "
                + tailLength + " cm tail";

        if (hasOwner) {
            str += ", owned by " + owner.getName();
        }

        return str;
    }

    public void setOwner(Owner owner) {
        if (owner == null || this.owner != null) {
            return;
        }

        this.owner = owner;
        hasOwner = true;
        owner.addDog(this);

    }

    public Owner getOwner() {
        return owner;
    }


    public boolean hasOwner() {
        return hasOwner;
    }

    public void removeOwner(){
        hasOwner = false;
        if(owner == null){
            return;
        }

        owner.removeDog(this);

        owner = null;
    }
}
