/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quotesbot;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author aleksic1540
 */
public class BrainyQuote {
    
    private String segment = "";
    private URLClass url;
    private String HTML = "";
    private ArrayList<String> quotesList = new ArrayList<>();
    public String getHTML(String website) throws Exception{
        java.net.URL url = new java.net.URL(website);
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = httpcon.getInputStream();
        int ptr = 0;
        StringBuffer buffer = new StringBuffer();
        while ((ptr = is.read()) != -1)
            buffer.append((char)ptr);
            this.HTML = buffer.toString();
            return buffer.toString();
    }
    
    public BrainyQuote(String url){
        this.url = new URLClass(url);
        try {
            getHTML(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        findQuotes();
    }
    public boolean isBrainyQuote(){
        return (url.toString().contains("brainyquote"));
    } 
    private int findWhenQuoteEnds(){
        
        return HTML.indexOf("/a></span><br>")-1;
    }
    private int findWhenQuoteStarts(){
        /*findIndexLoop:{for(int i = 0;i<HTML.length();i++){
            for(int j = 0;j!=4;j++){
                if(!Character.isDigit(HTML.charAt(i+j)) && HTML.charAt(i+j) == ".html".toCharArray()[j]){
                    System.out.println(HTML.charAt(i+j));
                    if(j == 3){
                        System.out.println(HTML.substring(i+7,i+30));
                        return i + 7;
                        }
                    }
                }
            }
        }
        */
        
        return HTML.indexOf("ml\">")+4;

        
    }
    private boolean isAlreadyAQuote(String quote){
        for(String i:quotesList){
            if(i.equalsIgnoreCase(quote)){
                return true;
            }
        }
        return false;
    }
    private void findQuotes(){
        //when the program finds the first index, it's going to just substring it and then do the same thing again
        //and again
        for(int i = 0; i != 19;i++){
            int index = findTheIndex();
            cutTheHTMLup(index);
            String theQuote = HTML.substring(findWhenQuoteStarts(),findWhenQuoteEnds());
            //System.out.println(theQuote);
            if(!isAlreadyAQuote(theQuote)){
                quotesList.add(theQuote);
            }
            
        }
    }
    public String getRandomQuote(){
        Random random = new Random();
        int randomNumber = random.nextInt((quotesList.size() - 1) + 1);
       return quotesList.get(randomNumber);
    }
    private int findTheIndex(){
        int index = HTML.indexOf("<span class=\"bqQuoteLink\"><a title=\"view quote\" href=\"");
        return index;
    }
    private void cutTheHTMLup(int indexGiven){
        if(indexGiven != -1 ){
                 HTML = HTML.substring(indexGiven+1);

               
        }
        
    }
    public String toString(){
        return url.toString();
    }
}
