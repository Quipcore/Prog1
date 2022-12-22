package assignments.lecture.other;

import project.*;

//Felix Lidö feli8145

import java.util.ArrayList;
import java.util.List;

public class AssignmentEightPointThree {

    private List<Owner> ownerList = new ArrayList<>();
    private DogList dogList = new DogList();
    private Reader reader = new Reader();


    public void giveDog(){

        String dogName = reader.readString("Enter the name of the dog");
        Dog dog = getDog(dogName);
        if(!dogList.contains(dog)){
            System.out.println("Error: no dog with that name");
            return;
        }

        if(dog.hasOwner()){
            System.out.println("Error: the dog already has an owner");
            return;
        }

        String ownerName = reader.readString("Enter the name of the new owner");
        Owner owner = getOwner(ownerName);
        if(owner == null){
            System.out.println("Error: no such owner");
            return;
        }

        owner.addDog(dog);


        System.out.printf("%s now owns %s\n", ownerName, dogName);
    }


    private Dog getDog(String dogName){
        for(int i = 0; i < dogList.size(); i++){
            Dog dog = dogList.get(i);
            if(dog.getName().equals(dogName)){
                return dog;
            }
        }
        return null;
    }



    private Owner getOwner(String ownerName){
        for(Owner owner : ownerList){
            if(owner.getName().equals(ownerName)){
                return owner;
            }
        }

        return null;
    }


}
