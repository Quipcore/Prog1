package techsupport.alva;

import project.Dog;

public class DogList {

    private Dog[] dogsArr;

    public DogList() {
        dogsArr = new Dog[0];
    }

    public void addDog(Dog dog) {
        if(checkDogExist(dog) || dog == null){
            return;
        }
        increaseArraySize();
        dogsArr[dogsArr.length-1] = dog;

    }

    private void increaseArraySize(){
        Dog[] newDogsArr = new Dog[dogsArr.length+1];
        for(int i = 0; i < dogsArr.length; i++) {
            newDogsArr[i] = dogsArr[i];
        }
        dogsArr = newDogsArr;
    }

    public boolean checkDogExist(Dog findDog) {

        if (findDog == null) {
            return false;
        }

        for (Dog dog : dogsArr) {
            if(dog.equals(findDog)){
                return true;
            }
        }

        return false;
    }

    public void removeDog(Dog dog) {

        if(!checkDogExist(dog)){
            return;
        }

        int index = checkIndex(dog);

        for (int i = index; i < dogsArr.length-1; i++){
            dogsArr[i] = dogsArr[i+1];
        }

        decreaseArraySize();

    }

    private int checkIndex(Dog dog) {
        for (int i = 0; i < dogsArr.length; i++){
            if(dogsArr[i].equals(dog)) {
                return i;
            }
        }
        return -1;
    }

    private void decreaseArraySize() {
        Dog[] newDogsArr = new Dog[dogsArr.length-1];
        for(int i = 0; i < dogsArr.length; i++) {
            newDogsArr[i] = dogsArr[i];
        }
        dogsArr = newDogsArr;
    }

}
