package assignments.lecture.other;

import project.Dog;

import java.util.ArrayList;
import java.util.List;

public class AssignmentSevenPointThree {

    private final List<Dog> dogList = new ArrayList<>();

    public Dog findDog(String name){
        for(Dog dog : dogList){
            if(dog.getName().equalsIgnoreCase(name)){
                return dog;
            }
        }
        return null;
    }

    public Dog aFindDog(String name){
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                return dog;
            }
        }
        return null;
    }

    public void addDog(Dog dog){
        dogList.add(dog);
    }






}
