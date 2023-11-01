//Funkar inte i Ilearn



import java.util.LinkedList;
import java.util.List;

public class SortedCharList {

    private List<Character> characterList = new LinkedList<>();
    public SortedCharList(){

    }

    public void add(char ch){
        characterList.add(ch);
        sort();
    }

    public void remove(char ch){
        characterList.remove(ch);
        sort();
    }

    public String toString() {
        String str = "";
        for(Character ch : characterList){
            str += ch;
        }
        return str;
    }

    private void sort(){
        characterList = characterList.stream().sorted().toList();
    }
}
