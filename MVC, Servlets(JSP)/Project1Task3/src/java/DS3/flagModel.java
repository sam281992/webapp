package DS3;

/*
 * 
 * This file is the Model component of the MVC, and it models the business
 * logic for the web application.  In this case, the business logic involves
 * making a request to CIAfactbook.com and then screen scraping the HTML that is
 * returned in order to fabricate an flag, name and text URL.
 * @author Samyak Mehta
 * @version 1.1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class flagModel {

    private String flagName; // The search string of the desired picture
    private String flagURL; // The URL of the picture image
    private String response ="";
    private String textTag;
    /**
     * Gets the flag image on fetching the url from CIAfactbook
     *
     * @param searchTag The URL to use to search the web.
     * @return returns the flag url
     */
    public String doCIASearch(String searchTag) 
            throws UnsupportedEncodingException  {
        
        // Create a URL for the page to be screen scraped
        String ciaURL = searchTag;
        
        // Fetch the page
        response = fetch(ciaURL);

       
        int cutLeft = response.indexOf("img src=\"../graphics/flags/large/");
        
        // If not found, then no such flag is available.
        if (cutLeft == -1) {
            flagURL = flagName = textTag = null;
            return null;
        }
        
        // Skip past this string. 
        cutLeft += "img src=\"../graphics/flags/large/".length();
       
        // Look for the close parenthesis
        int cutRight = response.indexOf("\"", cutLeft);

        // The actual URL where the picture can be found
        flagURL = "https://www.cia.gov/library/publications/the-world-factbook/graphics/flags/large/"+response.substring(cutLeft, cutRight);
        return flagURL;
       

    }
    /*
     * Return the picture name. ie the string 
     * 
     * @return The tag that was used to search for the current country name.
     */
    public String getPictureTag() {
        int cutLeft = response.indexOf("region_name1 countryName \">");
        // If not found, then no such name is available.
        if (cutLeft == -1) {
            flagName = flagURL = textTag = null;
            return null;
        }
        // Skip past this string. 
        cutLeft += "region_name1 countryName \">".length();
        
        // Look for the close parenthesis
        int cutRight = response.indexOf("<", cutLeft);
        flagName = response.substring(cutLeft, cutRight);
        return (flagName);
    }
    
     /*
     * Return the text tag. ie the string 
     * 
     * @return The text that was used to search for the current text.
     */
    public String getTextTag() {
        int cutLeft = response.indexOf("<span class=\"flag_description_text\">");
        // If not found, then no such photo is available.
        if (cutLeft == -1) {
            textTag = flagName = flagURL = null;
            return null;
        }
        // Skip past this string. 
        cutLeft += "<span class=\"flag_description_text\">".length();
        int cutRight = response.indexOf("<", cutLeft);
        textTag = response.substring(cutLeft, cutRight);
        return (textTag);
    }

    /*
     * Make an HTTP request to a given URL
     * 
     * @param urlString The URL of the request
     * @return A string of the response from the HTTP GET.  This is identical
     * to what would be returned from using curl on the command line.
     */
    private String fetch(String urlString) {
        String response = "";
        try {
            URL url = new URL(urlString);
            /*
             * Create an HttpURLConnection.  This is useful for setting headers
             * and for getting the path of the resource that is returned (which 
             * may be different than the URL above if redirected).
             * HttpsURLConnection (with an "s") can be used if required by the site.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Eeek, an exception");
            // Do something reasonable.  This is left for students to do.
        }
        return response;
    }
}
