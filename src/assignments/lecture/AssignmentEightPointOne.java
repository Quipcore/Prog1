package assignments.lecture;

import project.Owner;
import project.Reader;

import java.util.ArrayList;
import java.util.List;

public class AssignmentEightPointOne {
    private static List<Owner> ownerList = new ArrayList<>();
    private static final Reader reader = new Reader();


    public void addOwner(){
        String name = reader.readString("Name");
        ownerList.add(new Owner(name));
        System.out.printf("%s added to the register",name);
    }
}
