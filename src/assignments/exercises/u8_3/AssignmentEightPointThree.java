package assignments.exercises.u8_3;

// Felix Lidö feli8145

import java.util.ArrayList;
import java.util.List;

public class AssignmentEightPointThree {
    private List<Owner> ownerList = new ArrayList<>();
    private List<Dog> dogList = new ArrayList<>();

    private Reader reader = new Reader();


    public void giveDog(){
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
        System.out.printf("%s now owns %s",ownerName,dogName);

    }

    private String getString(String prompt) {
        while(true){
            String retString = reader.readString(prompt).trim();
            if(retString.length() > 0){
                return retString;
            }
            System.out.println("ERROR");
        }
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

}
