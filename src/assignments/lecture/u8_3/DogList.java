package assignments.lecture.u8_3;

// Felix Lidö feli8145
public class DogList {

    private Dog[] dogArray;

    public DogList(){
        dogArray = new Dog[0];
    }

    public boolean exists(){
        return dogArray.length != 0;
    }


    //--------------------------------------------------------------------------

    public void add(Dog dog) {
        if(contains(dog)){
            return;
        }
        increaseArraySize();
        dogArray[dogArray.length-1] = dog;
    }

    private void increaseArraySize(){
        Dog[] temp = new Dog[dogArray.length+1];
        for(int i = 0; i < dogArray.length; i++){
            temp[i] = dogArray[i];
        }

        dogArray = temp;
    }

    //-------------------------------------------------------------------------------------------------------

    public boolean contains(Dog searchDog){
        for(Dog dog : dogArray){
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

        for(int i = index; i < dogArray.length-1; i++){
            dogArray[i] = dogArray[i+1];
        }

        decreaseArraySize();

    }

    private int getIndex(Dog dog){
        for(int i = 0; i < dogArray.length; i++){
            if(dogArray[i].equals(dog)){
                return i;
            }
        }
        return -1;
    }

    private void decreaseArraySize(){
        Dog[] temp = new Dog[dogArray.length-1];

        for(int i = 0; i < temp.length; i++){
            temp[i] = dogArray[i];
        }

        dogArray = temp;

    }

    public Dog get(int index){
        return dogArray[index];
    }

    public void set(int index, Dog dog){
        dogArray[index] = dog;
    }

    public int size(){
        return dogArray.length;
    }

}