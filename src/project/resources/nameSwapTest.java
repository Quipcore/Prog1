package project.resources;

import project.Dog;

import java.util.ArrayList;
import java.util.List;

public class nameSwapTest {

    private static final List<Dog> dogList = new ArrayList<>();

    public static void main(String[] args) {
        dogList.add(new Dog("Flingan","Pudel",7,5));
        dogList.add(new Dog("Bamse","Dachshund",3,20));
        dogList.add(new Dog("Tyra","Golden",3,20));
        dogList.add(new Dog("Wilma","Dachshund",3,20));
        dogList.add(new Dog("Fido","Dachshund",3,20));


        sort();

        for(Dog dog : dogList){
            System.out.println(dog.toString());
        }
    }


    public static int sort(){

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

    //Problemet ligger här!!!
    private static int findSmallest(int startIndex){
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
    private static int swap(int i, int j){
        if(j < 0 || i < 0 || i == j){return 0;}

        Dog firstDog = dogList.get(i);
        Dog secondDog = dogList.get(j);

        dogList.set(i, secondDog);
        dogList.set(j,firstDog);
        return 1;
    }
}
