package project;
// Felix Lidö feli8145

public class DogList {

    private Dog[] dogArray;

    public DogList() {
        dogArray = new Dog[0];
    }


    //--------------------------------------------------------------------------

    public void add(Dog dog) {
        if (contains(dog) || dog == null) {
            return;
        }
        increaseArraySize();
        dogArray[dogArray.length - 1] = dog;
    }

    private void increaseArraySize() {
        Dog[] temp = new Dog[dogArray.length + 1];
        for (int i = 0; i < dogArray.length; i++) {
            temp[i] = dogArray[i];
        }

        dogArray = temp;
    }

    //-------------------------------------------------------------------------------------------------------

    public boolean contains(Dog searchDog) {
        for (Dog dog : dogArray) {
            if (dog.equals(searchDog)) {
                return true;
            }
        }

        return false;
    }

    //-------------------------------------------------------------------------------------------------------

    public void remove(Dog dog) {
        if (!contains(dog)) {
            return;
        }

        int index = indexOf(dog);

        for (int i = index; i < dogArray.length - 1; i++) {
            dogArray[i] = dogArray[i + 1];
        }

        decreaseArraySize();

    }

    private int indexOf(Dog dog) {
        for (int i = 0; i < dogArray.length; i++) {
            if (dogArray[i].equals(dog)) {
                return i;
            }
        }
        return -1;
    }

    private void decreaseArraySize() {
        Dog[] temp = new Dog[dogArray.length - 1];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = dogArray[i];
        }

        dogArray = temp;

    }

    public Dog get(int index) {
        return dogArray[index];
    }

    public int size() {
        return dogArray.length;
    }

    @Override
    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        for(int i = 0; i < dogArray.length; i++){
            strBuild.append(dogArray[i].getName());
            if(i != dogArray.length -1){
                strBuild.append(", ");
            }
        }
        return strBuild.toString();
    }
}
