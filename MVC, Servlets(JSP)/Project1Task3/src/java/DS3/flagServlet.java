/* 
 * 
 * This is a short example of MVC.
 * The welcome-file in the deployment descriptor (web.xml) points
 * to this servlet.  So it is also the starting place for the web
 * application.
 *
 * The servlet is acting as the controller.
 * There is one views - firstpage.jsp 
 * If there is a search parameter, then it searches for a 
 * picture and uses the firstpage.jsp view and displays the name of the country, flag and text.
 * The model is provided by flagModel.
 */
package DS3;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "flagServlet",
        urlPatterns = {"/flagServlet"})
public class flagServlet extends HttpServlet {

    flagModel ipm = null;  // The "business model" for this app

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        ipm = new flagModel();
    }

    // This servlet will reply to HTTP GET requests via this doGet method
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get the search parameter if it exists
        String search = request.getParameter("selecter_links");

        // This is the variable to help decide which view to be rendered.
        String nextView = null;
        /*
         * Check if the search parameter is present.
         * If there is a search parameter, then do the search and return the result.
         */
        if (search != null) {
            
            // returns the url back from doCIASearch
            String s = ipm.doCIASearch(search);
            /*
             * Attributes on the request object can be used to pass data to
             * the view.  These attributes are name/value pairs, where the name
             * is a String object.  Here the flag URL is passed to the view
             * after it is returned from the model doCIASearch method.
             */
            request.setAttribute("flagURL",s);

            // Pass the user search string (pictureTag) also to the view.
            request.setAttribute("flagTag", ipm.getPictureTag());
            request.setAttribute("textTag", ipm.getTextTag());
            nextView = "firstpage.jsp";
        } 
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}
