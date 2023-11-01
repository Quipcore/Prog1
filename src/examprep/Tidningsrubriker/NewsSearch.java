package Tidningsrubriker;

import java.util.ArrayList;

// Skriv metoden search i denna klass
public class NewsSearch {

    public ArrayList<SearchResult> search(String searchCriteria, ArrayList<Article> articles){
        ArrayList<SearchResult> searchResults = new ArrayList<>();

        for(Article article : articles){
            if(article.matches(searchCriteria)){
                searchResults.add(new SearchResult(article));
            }
        }
        return searchResults;
    }

}
