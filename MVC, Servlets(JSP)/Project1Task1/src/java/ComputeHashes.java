/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Samyak Mehta
 * @version 1.1
 */
@WebServlet(urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Gets the textbox value, stores it in a string.
        String text = request.getParameter("textbox");
        // Creating an object of the messagedigest class for hashing
        MessageDigest m;
        // Creating an byte array as the return value of update is a byte array
        byte[] digest = null;
        if(request.getParameter("radiobutton").equals("MD5"))
        {
            // Creates an instance of the MD5
            m = MessageDigest.getInstance("MD5");
            m.reset();
            // Updates the text to MD5
            m.update(text.getBytes());
            digest = m.digest();
        }
        
        else if(request.getParameter("radiobutton").equals("SHA-1"))
        {
            // Creates an instance of the MD5
            m = MessageDigest.getInstance("SHA-1");
            m.reset();
            // Updates the text to MD5
            m.update(text.getBytes());
            digest = m.digest();
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComputeHashes</title>");            
            out.println("</head>");
            out.println("<body>");
            // converts the MD5 or SHA-1 to base 64 and then to Hex.
            out.println("<h4>"+request.getParameter("radiobutton")+ "(base 64): " +javax.xml.bind.DatatypeConverter.printBase64Binary(digest)+"</h4>");
            out.println("<h4>"+request.getParameter("radiobutton")+ "(Hex): "+javax.xml.bind.DatatypeConverter.printHexBinary(digest)+"</h4>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
