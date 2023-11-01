package Tidningsrubriker;

public class Article {
    private String title;
    private String text;

    private Author author;

    public Article(String title, Author author, String text){

    }

    public boolean matches(String str){
        return true;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
