package assignments.lecture.other;

import project.Owner;

//Felix Lidö feli8145

import java.util.ArrayList;
import java.util.List;

public class AssignmentEightPointTwo{
    private List<Owner> ownerList = new ArrayList<>();

    public Owner findOwner(String name){
        for(Owner owner : ownerList){
            if(owner.getName().equalsIgnoreCase(name)){
                return owner;
            }
        }
        return null;
    }
}