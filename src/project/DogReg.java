package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DogReg {

    private final List<Dog> dogList = new ArrayList<>();
    private final Reader reader = new Reader();

    //-------------------------------------------------------------------------------------------------------

    public void registerDog(){

        String name = getInputString("Name");
        String breed = getInputString("Breed");

        int age = reader.readInt("Age");
        int weight = reader.readInt("Weight");

        dogList.add(new Dog(name, breed, age, weight));
        System.out.println(name + " added to the register");
    }

    private String getInputString(String prompt) {

        String input = "";
        boolean invalidInput = true;
        while(invalidInput){
            input = reader.readString(prompt).strip();

            invalidInput = input.chars().noneMatch(Character::isLetterOrDigit);
            if(invalidInput){
                System.out.println("Error: invalid input");
            }
        }
        return input;
    }

    //-------------------------------------------------------------------------------------------------------

    public void listDog() {
        if (dogList.isEmpty()) {
            System.out.println("Error: no dogs in register");
            return;
        }

        double minTail = reader.readDouble("Smallest tail length to display");

        List<Dog> validDogs = filterTails(minTail);
        if(validDogs.size() == 0){
            System.out.println("Error: no dog have a tail that long");
            return;
        }

        for (Dog dog : validDogs) {
            System.out.println(dog.toString());

        }
    }

    private List<Dog> filterTails(double tailLength){
        List<Dog> validDogs = new ArrayList<>();
        for(Dog dog : dogList){
            if (dog.getTailLength() >= tailLength){
                validDogs.add(dog);
            }
        }

        return validDogs;
    }

    //-------------------------------------------------------------------------------------------------------

    /** May return NULL!!!
     *
     * @param name
     * @return Dog
     */

    public Dog findDog(String name){
        for(Dog dog : dogList){
            if(dog.getName().equalsIgnoreCase(name)){
                return dog;
            }
        }
        return null;
    }

    //-------------------------------------------------------------------------------------------------------

    public void increaseAge(){
        String dogName = reader.readString("Enter the name of the dog");
        Dog foundDog = findDog(dogName);
        if(foundDog == null){
            System.out.println("Error: Null object");
            return;
        }

        foundDog.increaseAge();

        System.out.println(dogName + " is now one year older");
    }

    //-------------------------------------------------------------------------------------------------------

    public void removeDog(){
        String dogName = reader.readString("Enter the name of the dog");
        Dog dogToRemove = findDog(dogName);
        if(dogToRemove == null){
            System.out.println("Error: dog not found");
            return;
        }


        dogList.remove(dogToRemove);
        System.out.println(dogName + " is removed from the register");
    }

    //-------------------------------------------------------------------------------------------------------
    public int sort(){

        int swapAmount = 0;

        if(dogList.isEmpty()){
            return swapAmount;
        }

        for(int i = 0; i < dogList.size()-1; i++){
            int smallest = findSmallest(i);
            swapAmount += swap(i, smallest);
        }
        return swapAmount;
    }

    private int findSmallest(int startIndex){
        int retIndex = -1;
        double minTail = Integer.MAX_VALUE;

        for(int i = startIndex; i < dogList.size(); i++ ){
            double tailLengthToCheck = dogList.get(i).getTailLength();
            if(tailLengthToCheck < minTail){
                minTail = tailLengthToCheck;
                retIndex = i;
            } else if (tailLengthToCheck == minTail) {
                int lexCompareResult = dogList.get(i).getName().compareToIgnoreCase(dogList.get(retIndex).getName());
                if(lexCompareResult < 0){
                    retIndex = i;
                }
            }
        }

        return retIndex;

    }

    /**
     * Returns 1 if swap is made otherwise returns 0.
     * @param i
     * @param j
     * @return
     */
    private int swap(int i, int j){
        if(j < 0 || i < 0 || i == j){return 0;}

        Dog firstDog = dogList.get(i);
        Dog secondDog = dogList.get(j);

        dogList.set(i, secondDog);
        dogList.set(j,firstDog);
        return 1;
    }

    private void libSwap(int i, int j){
        if(j < 0){return;}
        Collections.swap(dogList,i,j);
    }
}