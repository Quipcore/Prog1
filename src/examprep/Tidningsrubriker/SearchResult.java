package Tidningsrubriker;

// Skriv konstruktorn till denna klass
public class SearchResult {

    private final String title;
    private final String author;
    private final String firstParagraph;

    public SearchResult(Article article){
        this.title = article.getTitle();
        this.author = article.getAuthor().getName();
        String text = article.getText();
        this.firstParagraph = text.contains("\n") ? text.substring(0, text.indexOf("\n")) : text;

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getFirstParagraph() {
        return firstParagraph;
    }

}
