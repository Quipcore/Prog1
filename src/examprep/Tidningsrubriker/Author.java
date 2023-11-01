package Tidningsrubriker;

// Skriv metoden matches i denna klass
public class Author {

    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean matches(String searchStr){
        return name.toLowerCase().contains(searchStr.toLowerCase());
    }

}
