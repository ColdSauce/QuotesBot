/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quotesbot;

/**
 *
 * @author aleksic1540
 */
public class URLClass {
    private String URL = "";
    public String toString(){
        return this.URL;
    }
    public URLClass(String URL){
        this.URL = URL;
    }
    public String findAddress(){
        if(URL.contains("http://")){
            if(URL.contains("www.")){
                return URL.substring(11);
            }
            else{
                return URL.substring(7);
            }
        }
        else if(URL.contains("https://"))
            if (URL.contains("www."))
                return URL.substring(12);
            else
                return URL.substring(8);
        return "DIDN'T WORK AT ALL OMG OMG OMG OM GOM GOM GOM GOM GOM";
    }
}
