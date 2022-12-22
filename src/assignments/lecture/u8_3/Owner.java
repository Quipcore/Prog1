package assignments.lecture.u8_3;
// Felix Lidö feli8145

public class Owner {
    private String name;
    //private List<Dog> ownedDogs = new ArrayList<>();
    private DogList ownedDogs = new DogList();

    public Owner(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }
    public void addDog(Dog dogToAdd){
        if(dogToAdd.hasOwner()){
            System.out.println("Error: the dog already has an owner");
        }
        ownedDogs.add(dogToAdd);
    }
}
