package assignments.exercises.u8_8;

// Felix Lidö feli8145

public class Owner {
    private String name;
    private DogList ownedDogs = new DogList();

    public Owner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public void addDog(Dog dogToAdd) {
        Owner dogsOwner = dogToAdd.getOwner();
        //if dogsOwner == null -> then dogs has no owner

        if(dogsOwner != null){
            if(dogsOwner.equals(this) && !ownedDogs.contains(dogToAdd)){
                ownedDogs.add(dogToAdd);
            }
            return;
        }

        if(!ownedDogs.contains(dogToAdd)){
            ownedDogs.add(dogToAdd);
        }

        dogToAdd.setOwner(this);

    }

    public boolean hasDog(Dog dog) {
        return ownedDogs.contains(dog);
    }

    public DogList getDogList(){
        return ownedDogs;
    }


    public void removeDog(Dog dog){
        if(dog.getOwner() != this){
            return;
        }
        ownedDogs.remove(dog);
        if(dog.hasOwner()){
            dog.removeOwner();
        }
   }
}
