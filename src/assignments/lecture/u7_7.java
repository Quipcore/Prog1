package assignments.lecture;

import project.Dog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class u7_7{
    private final List<Dog> dogList = new ArrayList<>();

    public void sort(){
        for(int i = 0; i < dogList.size()-1; i++){
            swap(i,findSmallest(i));
        }
    }

    public int findSmallest(int startIndex){
        int retIndex = -1;
        double minTail = dogList.get(startIndex).getTailLength();

        for(int n = 0; n < dogList.size(); n++ ){
            if(dogList.get(n).getTailLength() < minTail){
                minTail = dogList.get(n).getTailLength();
                retIndex = n;
            }
        }

        return retIndex;
    }

    public void swap(int i, int j){
        if(j < 0){return;}
        Dog dogI = dogList.get(i);
        Dog dogJ = dogList.get(j);
        dogList.set(i, dogJ);
        dogList.set(j,dogI);

    }

    public void libSwap(int i, int j){
        if(j < 0){return;}
        Collections.swap(dogList,i,j);
    }
}