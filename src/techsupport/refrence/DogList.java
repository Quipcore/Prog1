package techsupport.refrence;

//Felix Lidö feli8145

import project.Dog;

public class DogList {

    private Dog[] dogArr;

    public DogList(){
        dogArr = new Dog[0];
    }


    //--------------------------------------------------------------------------

    public void add(Dog dog) {
        if(contains(dog) || dog == null){
            return;
        }
        increaseArraySize();
        dogArr[dogArr.length-1] = dog;
    }

    private void increaseArraySize(){
        Dog[] temp = new Dog[dogArr.length+1];
        for(int i = 0; i < dogArr.length; i++){
            temp[i] = dogArr[i];
        }

        dogArr = temp;
    }

    //-------------------------------------------------------------------------------------------------------

    public boolean contains(Dog searchDog){
        for(Dog dog : dogArr){
            if(dog.equals(searchDog)){
                return true;
            }
        }

        return false;
    }

    //-------------------------------------------------------------------------------------------------------

    public void remove(Dog dog){
        if(!contains(dog)){
            return;
        }

        int index = getIndex(dog);

        for(int i = index; i < dogArr.length-1; i++){
            dogArr[i] = dogArr[i+1];
        }

        decreaseArraySize();

    }

    private int getIndex(Dog dog){
        for(int i = 0; i < dogArr.length; i++){
            if(dogArr[i].equals(dog)){
                return i;
            }
        }
        return -1;
    }

    private void decreaseArraySize(){
        Dog[] temp = new Dog[dogArr.length-1];

        for(int i = 0; i < temp.length; i++){
            temp[i] = dogArr[i];
        }

        dogArr = temp;

    }
}
