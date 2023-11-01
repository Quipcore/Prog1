package project;
//Felix Lidö feli8145
import java.util.ArrayList;
import java.util.List;

public class Dogregister {


    private static final String[] MENU_ITEMS = {
            "Register dog",
            "Register owner",
            "Remove dog",
            "Remove owned dog",
            "Remove owner",
            "Increase age",
            "Give dog",
            "List dogs",
            "List Owners",
            "Exit"
    };

    private List<Dog> dogList = new ArrayList<>();
    private List<Owner> ownerList = new ArrayList<>();

    private Reader reader = new Reader();

    //-------------------------------------------------------------------------------------------------------


    public void runMenu() {
        boolean exitCondition = false;
        while(!exitCondition){
            printMenu();
            String menuSelected = getInputString("Enter menuselection");

            switch (menuSelected) {
                case "register new dog" -> registerDog();
                case "register new owner" -> registerOwner();
                case "remove dog" -> removeDog();
                case "remove owned dog" -> removeOwnedDog();
                case "remove owner" -> removeOwner();
                case "increase age" -> increaseAge();
                case "give dog" -> giveDog();
                case "list dogs" -> listDogs();
                case "list owners" -> listOwners();
                case "exit" -> exitCondition = true;
                default -> System.out.println("Error: Not a valid selection");
            }
            System.out.println();
        }
        System.out.println("Exiting program");
    }

    private void printMenu() {
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            String menuItem = MENU_ITEMS[i].trim();
            if (!menuItem.equals("")) {
                System.out.printf("%d. %s\n", i + 1, menuItem);
            }
        }
        System.out.println();
    }

    //-------------------------------------------------------------------------------------------------------

    private void registerDog() {
        String dogName = getInputString("Name");
        String dogBreed = getInputString("Breed");

        int age = reader.readInt("Age");
        int weight = reader.readInt("Weight");

        dogList.add(new Dog(dogName, dogBreed, age, weight));
        System.out.println(dogName + " added to the register");

    }

    //-------------------------------------------------------------------------------------------------------

    private void registerOwner() {
        String name = getInputString("Name");
        ownerList.add(new Owner(name));
        System.out.printf("%s added to the register", name);
    }

    //-------------------------------------------------------------------------------------------------------

    private void removeDog() {
        String dogName = getInputString("Enter the name of the dog");

        Dog dogToRemove = getDog(dogName);
        if (dogToRemove == null) {
            System.out.println("Error: dog not found");
            return;
        }

        Owner owner = dogToRemove.getOwner();
        if (owner != null) {
            owner.removeDog(dogToRemove);
        }

        dogList.remove(dogToRemove);
        System.out.println(dogName + " is removed from the register");
    }

    //-------------------------------------------------------------------------------------------------------

    private void removeOwnedDog() {
        String dogName = getInputString("Enter the name of the dog");

        Dog dog = getDog(dogName);
        if (dog == null) {
            System.out.println("Error: no dog with that name");
            return;
        }

        if (!dog.hasOwner()) {
            System.out.println("Error: Dog has no owner");
            return;
        }
        String ownersName = dog.getOwner().getName();
        dog.removeOwner();

        System.out.printf("%s is removed %s", dog.getName(), ownersName);
    }

    //-------------------------------------------------------------------------------------------------------

    //LOOK OVER THE USE OF owner.getDogList();
    private void removeOwner() {
        String ownerName = getInputString("Enter the name of the owner");

        Owner owner = getOwner(ownerName);
        if (owner == null) {
            System.out.println("Error: no owner with that name");
            return;
        }

        dogList.removeIf(dog -> dog.getOwner().equals(owner));

        ownerList.remove(owner);

        System.out.printf("%s is removed ", ownerName);
    }

    //-------------------------------------------------------------------------------------------------------

    private void increaseAge() {
        String dogName = reader.readString("Enter the name of the dog");
        Dog foundDog = getDog(dogName);

        if (foundDog == null) {
            System.out.println("Error: Null object");
            return;
        }

        foundDog.increaseAge();

        System.out.println(dogName + " is now one year older");
    }

    //-------------------------------------------------------------------------------------------------------

    private void giveDog() {
        String dogName = getInputString("Enter the name of the dog");

        Dog dog = getDog(dogName);
        if (dog == null) {
            System.out.println("Error: no dog with that name");
            return;
        }

        if (dog.hasOwner()) {
            System.out.println("Error: Dog has owner");
            return;
        }

        String ownerName = getInputString("Enter the name of the owner");

        Owner owner = getOwner(ownerName);
        if (owner == null) {
            System.out.println("Error: no owner with that name");
            return;
        }

        owner.addDog(dog);
        System.out.printf("%s now owns %s", ownerName, dogName);
    }

    //-------------------------------------------------------------------------------------------------------

    private void listDogs() {
        if (dogList.isEmpty()) {
            System.out.println("Error: no dogs in register");
            return;
        }

        sort();
        double minTail = reader.readDouble("Smallest tail length to display");

        List<Dog> validDogs = filterTails(minTail);
        if (validDogs.size() == 0) {
            System.out.println("Error: no dog have a tail that long");
            return;
        }

        for (Dog dog : validDogs) {
            System.out.println(dog.toString());

        }
    }

    private void sort(){

        if(dogList.isEmpty()){
            return;
        }

        for(int i = 0; i < dogList.size()-1; i++){
            int smallest = findSmallest(i);
            swap(i, smallest);
        }
    }

    private int findSmallest(int startIndex){
        int retIndex = -1;
        double minTail = Integer.MAX_VALUE;

        for(int i = startIndex; i < dogList.size(); i++ ){
            double tailLengthToCheck = dogList.get(i).getTailLength();
            if(tailLengthToCheck < minTail){
                minTail = tailLengthToCheck;
                retIndex = i;
            } else if (tailLengthToCheck == minTail) { //Compare the names of dogs with same taillength
                int lexCompareResult = dogList.get(i).getName().compareToIgnoreCase(dogList.get(retIndex).getName());
                if(lexCompareResult < 0){
                    retIndex = i;
                }
            }
        }

        return retIndex;
    }

    private void swap(int i, int j){
        if(j < 0 || i < 0 || i == j){return;}

        Dog firstDog = dogList.get(i);
        Dog secondDog = dogList.get(j);

        dogList.set(i, secondDog);
        dogList.set(j,firstDog);
    }

    //-------------------------------------------------------------------------------------------------------

    private void listOwners() {
        if (ownerList.isEmpty()) {
            System.out.println("Error: no owners in list");
            return;
        }

        for (Owner owner : ownerList) {
            DogList ownersDogs = new DogList();
            for(Dog dog : dogList){
                Owner dogsOwner = dog.getOwner();
                if(dogsOwner != null && dogsOwner.equals(owner)){
                    ownersDogs.add(dog);
                }
            }
            System.out.printf("%s [%s]\n", owner.getName(), ownersDogs);
        }
    }

    //-------------------------------------------------------------------------------------------------------
    //----------------------------------EXTRA HELP FUNCTIONS-------------------------------------------------
    //-------------------------------------------------------------------------------------------------------

    private String getInputString(String prompt) {
        while (true) {
            String input = reader.readString(prompt).strip();

            boolean validInput = input.chars().anyMatch(Character::isLetterOrDigit);
            if (validInput) {
                return input;
            }

            System.out.println("Error: invalid input");
        }
    }

    //-------------------------------------------------------------------------------------------------------

    private List<Dog> filterTails(double tailLength) {
        List<Dog> validDogs = new ArrayList<>();
        for (Dog dog : dogList) {
            if (dog.getTailLength() >= tailLength) {
                validDogs.add(dog);
            }
        }

        return validDogs;
    }

    //-------------------------------------------------------------------------------------------------------

    private Dog getDog(String dogName) {
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(dogName)) {
                return dog;
            }
        }
        return null;
    }

    //-------------------------------------------------------------------------------------------------------

    private Owner getOwner(String ownerName) {
        for (Owner owner : ownerList) {
            if (owner.getName().equalsIgnoreCase(ownerName)) {
                return owner;
            }
        }
        return null;
    }
}
