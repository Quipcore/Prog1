package assignments.exercises.u8_6;

//Felix Lidö feli8145

import java.util.ArrayList;
import java.util.List;

public class AssignmentEightPointSix {


    private List<Owner> ownerList = new ArrayList<>();
    private List<Dog> dogList = new ArrayList();
    private Reader reader = new Reader();

    public void removeDog() {
        String dogName = getString("Enter the name of the dog");

        Dog dog = getDog(dogName);
        if (dog == null) {
            System.out.println("Error: no dog with that name");
            return;
        }

        if (!dog.hasOwner()) {
            System.out.println("Error: Dog has no owner");
            return;
        }
        String ownersName = dog.getOwner().getName();
        dog.removeOwner();

        System.out.printf("%s is removed %s", dog.getName(), ownersName);
    }

    public void addDog() {
        String dogName = getString("Enter the name of the dog");

        Dog dog = getDog(dogName);
        if (dog == null) {
            System.out.println("Error: no dog with that name");
            return;
        }

        if (dog.hasOwner()) {
            System.out.println("Error: Dog has owner");
            return;
        }

        String ownerName = getString("Enter the name of the owner");

        Owner owner = getOwner(ownerName);
        if (owner == null) {
            System.out.println("Error: no owner with that name");
            return;
        }

        owner.addDog(dog);
        System.out.printf("%s now owns %s\n", ownerName, dogName);

    }

    private String getString(String prompt) {
        String retString = "";
        while (retString.length() == 0) {
            retString = reader.readString(prompt).trim();
            if (retString.length() == 0) {
                System.out.println("ERROR");
            }
        }
        return retString;
    }

    private Dog getDog(String dogName) {
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(dogName)) {
                return dog;
            }
        }
        return null;
    }

    private Owner getOwner(String ownerName) {
        for (Owner owner : ownerList) {
            if (owner.getName().equalsIgnoreCase(ownerName)) {
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
        if (validDogs.size() == 0) {
            System.out.println("Error: no dog have a tail that long");
            return;
        }

        for (Dog dog : validDogs) {
            System.out.println(dog.toString());

        }
    }

    //-------------------------------------------------------------------------------------------------------
    private List<Dog> filterTails(double tailLength) {
        List<Dog> validDogs = new ArrayList<>();
        for (Dog dog : dogList) {
            if (dog.getTailLength() >= tailLength) {
                validDogs.add(dog);
            }
        }

        return validDogs;
    }

}
