package examprep;

import java.util.ArrayList;
import java.util.List;

public class prep {

    class Cat{
        public Cat(){

        }

        public Cat(Owner owner){

        }

        public Owner getOwner(){
            return null;
        }
    }

    class Owner{
        private String name;

        public Owner(String name){

        }

        public String getName(){
            return "";
        }

        public void setName(String name){

        }

    }

    public static void main(String[] args) {

    }
    public ArrayList<Character> list(){

        ArrayList<Character> charList = (ArrayList<Character>) List.of('a','b','c');
        return charList;
    }

}
