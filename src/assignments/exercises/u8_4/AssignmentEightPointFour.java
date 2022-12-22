package assignments.exercises.u8_4;

//Felix Lidö feli8145

import java.util.ArrayList;
import java.util.List;

public class AssignmentEightPointFour {

    private List<Owner> ownerList = new ArrayList<>();
    private List<Dog> dogList = new ArrayList();
    private Reader reader = new Reader();


    public AssignmentEightPointFour(){
        Dog d1 = new Dog("s","e",12,12);
        Owner o1 = new Owner("j");

        dogList.add(d1);
        ownerList.add(o1);
    }

    public void addDog(){
        String dogName = getString("Enter the name of the dog");

        Dog dog = getDog(dogName);
        if(dog == null){
            System.out.println("Error: no dog with that name");
            return;
        }

        if(dog.hasOwner()){
            System.out.println("Error: Dog has owner");
            return;
        }

        String ownerName = getString("Enter the name of the owner");

        Owner owner = getOwner(ownerName);
        if(owner == null){
            System.out.println("Error: no owner with that name");
            return;
        }

        owner.addDog(dog);
        System.out.printf("%s now owns %s\n",ownerName,dogName);

    }

    private String getString(String prompt) {
        String retString = "";
        while(retString.length() == 0){
            retString = reader.readString(prompt).trim();
            if(retString.length() == 0){
                System.out.println("ERROR");
            }
        }
        return retString;
    }


    private Dog getDog(String dogName){
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(dogName)) {
                return dog;
            }
        }
        return null;
    }


    private Owner getOwner(String ownerName){
        for(Owner owner : ownerList){
            if(owner.getName().equalsIgnoreCase(ownerName)){
                return owner;
            }
        }
        return null;
    }

    public void listOwners() {

        if (ownerList.size() == 0) {
            System.out.println("Error: no owners in list");
            return;
        }

        for (Owner owner : ownerList) {
            DogList ownersDogs = owner.getDogList();
            System.out.printf("%s [%s]\n", owner.getName(), ownersDogs.toString());

        }

    }

    public void listDogs() {
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

    //-------------------------------------------------------------------------------------------------------
    private List<Dog> filterTails(double tailLength){
        List<Dog> validDogs = new ArrayList<>();
        for(Dog dog : dogList){
            if (dog.getTailLength() >= tailLength){
                validDogs.add(dog);
            }
        }

        return validDogs;
    }
}