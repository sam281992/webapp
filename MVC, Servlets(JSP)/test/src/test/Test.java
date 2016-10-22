package test;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Test {

    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port we are using
             
            // Create a new server socket
            ServerSocket listenSocket = new ServerSocket(serverPort);
            
            /*
             * Block waiting for a new connection request from a client.
             * When the request is received, "accept" it, and the rest
             * the tcp protocol handshake will then take place, making 
             * the socket ready for reading and writing.
             */
            while(true){
            clientSocket = listenSocket.accept();
            // If we get here, then we are now connected to a client.
            
            // Set up "in" to read from the client socket
            Scanner in;
            in = new Scanner(clientSocket.getInputStream());
            
            // Set up "out" to write to the client socket
            PrintWriter out;
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
            
            //this code will print on the browser screen!
            // out.close();

            /* 
             * Forever,
             *   read a line from the socket
             *   print it to the console
             *   echo it (i.e. write it) back to the client
             */
                String data = in.nextLine();
                System.out.println("Echoing: " + data);
                int cutLeft = data.indexOf("GET");
                int end = data.indexOf(" HTTP/1.1");
                cutLeft+= "GET".length();
                
                String fileName = data.substring(cutLeft+2, end);
                System.out.println("FILE NAME:"+fileName);
                try
              {
                Scanner text = new Scanner (new File(fileName));
                StringBuilder fileContent = new StringBuilder();
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                 while (text.hasNextLine()) 
                 {
			out.println(text.nextLine()+"\n");
                 }
                 System.out.print(fileContent.toString());
                
                out.println("\r\n");
                out.flush();
            }
                
                catch(Exception exception)
                {
                    System.out.println("404 : File not found exception caught");
                     out.println("HTTP/1.1 404 Not found\n\n");
                                
                      out.println("<html>File not found</html>");
                      out.flush();
                }
        }
        // Handle exceptions
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());
            
        // If quitting (typically by you sending quit signal) clean up sockets
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }
    }
}