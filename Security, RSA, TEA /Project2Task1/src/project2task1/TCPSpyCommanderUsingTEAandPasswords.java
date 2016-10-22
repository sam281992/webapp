/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2task1;

/**
 * The TCP server will connect to the client through a TCP socket and takes encrypted information from the client and decrypt it using its symmetric key.   
 * @author Samyak
 * @version 1.0
 */
import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TCPSpyCommanderUsingTEAandPasswords {
	public static void main (String args[]) {
                try{
			int counter =0;
                        // Creating default username and locations array to be written in the default Kml file initially. 
                        String []usernames = {"mikem","jamesb","joem"};
                        String []locations = {"-79.948460,40.444501,0.00000","-79.940450,40.437394,0.0000","-79.945389,40.444216,0.00000"};
                        
                        
                        //Storing hashing table of passwords 
                        HashPassword hp = new HashPassword();
                        HashMap  <String, String> map = hp.initializePassword();
                        
                        // Entering the symmetric key for the client.
                        Scanner sc1 = new Scanner(System.in);
                        System.out.println("Enter Symmetric Key");
                        String symmetricKey = sc1.nextLine();
			
                        System.out.println("Waiting for spies to visit...");
                        
                        // the server port is set up and the the socket is set up.
                        int serverPort = 7896; 
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
                                counter++;
				Connection c = new Connection(clientSocket, symmetricKey, counter, usernames,locations, map, hp);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
/**
 * Runs the actual server logic on every connection set up with the user.
 * @author Samyak
 */
class Connection extends Thread {
	DataInputStream in;
        //DataInputStream in1;
	DataOutputStream out;
	Socket clientSocket;
        String symmetricKey = null;
        String []usernames = new String[3];
        String []locations = new String[3];
        String symmetricKeyValue = "";
        int counter =0;
        HashPassword hp;
        HashMap<String, String> map = new HashMap<String, String>();
	public Connection (Socket aClientSocket, String symmetricKey, int counter, String [] usernames, String [] locations, HashMap<String, String> map, HashPassword hp ) {
		try {
                        // initialising the socket and multiple variables coming in from the client
                        clientSocket = aClientSocket;
                        this.symmetricKey = symmetricKey; 
                        this.counter = counter;
                        this.usernames = usernames;
                        this.locations = locations;
                        this.map = map;
                        this.hp = hp;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
                        //in1 = new DataInputStream( clientSocket.getInputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			              
			
                        // reading the username and password
                        int length1 = in.readInt();
                        byte [] userPass = new byte[length1];
                        in.readFully(userPass);
                        
                        // reading the location
                        int length2 = in.readInt();
                        byte [] ne = new byte[length2];
                        in.readFully(ne);
                        
                        // Decrypting the id and password
                        String usernamePassword = decrypt(userPass, symmetricKey);
                        
                        
                        // Checking for symmetric key values by decrypting the password
                        //checkingSymmetricKey using ASCII
                        int i = 0;
                        while(i < usernamePassword.length())
                        {
                            if(usernamePassword.charAt(i)>128 || usernamePassword.charAt(i)<0)
                            {
                                symmetricKeyValue = "wrong";
                                break;
                            }
                            i++;
                        }
                          
                        // Authenticating the symmetric Key, username and password
                        if (!symmetricKeyValue.equals("wrong"))
                        {  
                            String values[] = usernamePassword.split(" ");
                        
                            // Decrypting the location 
                            String orgmessage = decrypt(ne, symmetricKey);
                       
                       
                            // Generating the hash function
                            String passHash = hp.hashingFunc(values[1]+"XYsTrP");
                            
                            
                           // Checking the username and password.
                           if((values[0].equals("mikem") && map.get(values[0]).equals(passHash)) || (values[0].equals("jamesb") && map.get(values[0]).equals(passHash)) || (values[0].equals("joem") && map.get(values[0]).equals(passHash)))
                            {
                            System.out.println("Got visit "+counter+" from "+values[0]);
                            FileWrite(values[0],orgmessage);
                            out.writeUTF("Thank you. Your location was securely transmitted to Intelligence Headquarters.");
                            }
                            else
                            {
                                System.out.println("Got visit "+counter+" from "+values[1]+". Illegal Password attempt. This may be an attack.");
                                FileWrite(usernames,locations);
                                out.writeUTF("Not a valid user-id or password.");
                            }
                        }
                        // Writes a message on the server suggesting an illegal symmetric key. Also at this point the KML file isnt written to the system.
                        else
                        {
                            System.out.println("Got visit "+counter+ " illegal symmetric key used. This may be an attack.");
                            out.writeUTF("");
                            clientSocket.close();
                        }
                     
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch(IOException e) {System.out.println("readline:"+e.getMessage());
                }catch(RuntimeException e){System.out.println(e.getMessage());
                    try {
                        clientSocket.close();
                        System.exit(0);
                    } catch (IOException ex) {
                        
                    }
		} catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
        /**
         * Decrypts the encrypted message using the symmetric Key and TEA algorithm.
         * @param encrpytmessage the message encrypted on the client side
         * @param symmetrickey the tea symmetric key
         * @return 
         */
        public String decrypt(byte[] encrpytmessage, String symmetrickey)
        {
            byte[] originalSymm = symmetrickey.getBytes();
            Tea t = new Tea(originalSymm);
            byte[] decrypt = t.decrypt(encrpytmessage);
            String decryptMessage = new String(decrypt);
            return decryptMessage;
        }
        /**
         * Writes the username and password in the KML file, when authenticated clients transmit location and username
         * @param name is the username of the user
         * @param location is the location of the user 
         * @throws FileNotFoundException
         * @throws UnsupportedEncodingException 
         */
        public void FileWrite(String name, String location) throws FileNotFoundException, UnsupportedEncodingException{
            
            PrintWriter writer = new PrintWriter("SecretAgents.kml", "UTF-8");   
             if(name.equals("mikem"))
                {
                    usernames[0] = name;
                    locations[0] = location;
                }
                
                else if(name.equals("jamesb"))
                {
                    usernames[1] = name;
                    locations[1] = location;
                }
                                            
                else if(name.equals("joem"))
                {
                     usernames[2] = name;
                     locations[2] = location;                      
                }
            writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                                         "<kml xmlns=\"http://earth.google.com/kml/2.2\"\n" +
                                            "><Document>\n" +
                                            "<Style id=\"style1\">\n" +
                                            "<IconStyle>\n" +
                                            "<Icon>\n" +
                                            "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue- dot.png</href>\n" +
                                            "</Icon> </IconStyle> </Style> <Placemark>\n" +
                                            "<name>seanb</name>\n" +
                                            "<description>Spy Commander</description> <styleUrl>#style1</styleUrl>\n" +
                                            "<Point>\n" +
                                            "<coordinates>-79.945289,40.44431,0.00000</coordinates> </Point>\n" +
                                            "</Placemark> <Placemark>\n");
                                           
                                         
            writer.print("<name>"+usernames[0]+"</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                                            "<coordinates>"+locations[0]+"</coordinates> </Point>\n" +
                                            "</Placemark><Placemark>\n");
                                            
                                            
            
            writer.print("<name>"+usernames[1]+"</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                                            "<coordinates>"+locations[1]+"</coordinates> </Point>\n" +
                                            "</Placemark><Placemark>\n");
            
            writer.print("<name>"+usernames[2]+"</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                                            "<coordinates>"+locations[2]+"</coordinates> </Point>\n" +
                                            "</Placemark>\n");
                                            
                            writer.print("</Document>\n" +"</kml>");
                            writer.close();
                            
        }
        /**
         * When a no spy is properly authenticated. It prints the default KML file the first time.
         * @param usernames is the original username array.
         * @param locations is the original location array.
         * @throws FileNotFoundException
         * @throws UnsupportedEncodingException 
         */
        public void FileWrite(String [] usernames, String[] locations) throws FileNotFoundException, UnsupportedEncodingException{
            
            PrintWriter writer = new PrintWriter("SecretAgents.kml", "UTF-8");
            writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                                         "<kml xmlns=\"http://earth.google.com/kml/2.2\"\n" +
                                            "><Document>\n" +
                                            "<Style id=\"style1\">\n" +
                                            "<IconStyle>\n" +
                                            "<Icon>\n" +
                                            "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue- dot.png</href>\n" +
                                            "</Icon> </IconStyle> </Style> <Placemark>\n" +
                                            "<name>seanb</name>\n" +
                                            "<description>Spy Commander</description> <styleUrl>#style1</styleUrl>\n" +
                                            "<Point>\n" +
                                            "<coordinates>-79.945289,40.44431,0.00000</coordinates> </Point>\n" +
                                            "</Placemark> <Placemark>\n");
                                           
                                          
            writer.print("<name>"+usernames[0]+"</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                                            "<coordinates>"+locations[0]+"</coordinates> </Point>\n" +
                                            "</Placemark> <Placemark>\n");
                                            
                                            
                                            
            writer.print("<name>"+usernames[1]+"</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                                            "<coordinates>"+locations[1]+"</coordinates> </Point>\n" +
                                            "</Placemark> <Placemark>\n");
                                            
            writer.print("<name>"+usernames[2]+"</name> <description>Spy</description> <styleUrl>#style1</styleUrl> <Point>\n" +
                                            "<coordinates>"+locations[2]+"</coordinates> </Point>\n" +
                                            "</Placemark>\n");
     
                            writer.print("</Document>\n" +"</kml>");
                            writer.close();
                            
        }
        
        
}