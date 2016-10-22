package clickerPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

// allowing the user to access the getresult url too
@WebServlet(name = "submit",
        urlPatterns = {"/submit","/getResults"})
public class submit extends HttpServlet {

    clickModel cm = null;  // The "business model" for this app
    // Initiate this servlet by instantiating the model that it will use.
    
    // Setting up an options counter
    int count[] = new int[4];
    
    // initialising the model 
    @Override
    public void init() {
        cm = new clickModel();
    }
     
    /**
     * 
     * @param request is the request passed from the post.
     * @param response is the response 
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       // Stores the value of the radio button
        String radio = request.getParameter("radiobutton");

        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        // Stores the value of the next view
        String nextView;
        // Checks if a value of the radio button
        if(radio != null)
        {
            count = cm.count(radio);
            request.setAttribute("radioTag", radio);
            nextView = "firstpage.jsp";
        }
        // render the same page if no option selected.
        else
        {
            nextView ="firstpage.jsp";
        }
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);

    }
    /**
     * 
     * @param request gets the get request when url typed on the address bar
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String ua = request.getHeader("User-Agent");
         
        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        //decides the view to be sent to after the operations are done
        String nextView ="";
        // checks if the addressbar mentions getresults or is loading the first page.
        if(request.getServletPath().equals("/getResults"))
        {
            // sets the attribute of the request parameter with values of the counter.
            request.setAttribute("radioTag1", count[0]);
            request.setAttribute("radioTag2", count[1]);
            request.setAttribute("radioTag3", count[2]);
            request.setAttribute("radioTag4", count[3]);
            // sets the view to result page
            nextView = "resultPage.jsp";
        }
        else
        {
            // sets the view back to the front page
            nextView ="firstpage.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
       
       
    }
 }
