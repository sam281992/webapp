/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2task2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The commander is the server that decrypts the message using RSA and TEA. 
 * @author Samyak
 * @version 1.0
 */
public class TCPSpyCommanderUsingTEAandPasswordsAndRSA {
	public static void main (String args[]) {
                try{
			int counter =0;
                        String []usernames = {"mikem","jamesb","joem"};
                        String []locations = {"-79.948460,40.444501,0.00000","-79.940450,40.437394,0.0000","-79.945389,40.444216,0.00000"};
                        Scanner sc = new Scanner(System.in);
                        BigInteger n,e,d; 
                        
                         //Storing hashing table of passwords 
                        HashPassword hp = new HashPassword();
                        HashMap  <String, String> map = hp.initializePassword();   
                        Random rnd = new Random();

                             // Step 1: Generate two large random primes.
                             // We use 400 bits here, but best practice for security is 2048 bits.
                             // Change 400 to 2048, recompile, and run the program again and you will
                             // notice it takes much longer to do the math with that many bits.
                             BigInteger p = new BigInteger(400,100,rnd);
                             BigInteger q = new BigInteger(400,100,rnd);
                             // Step 2: Compute n by the equation n = p * q.
                             n = p.multiply(q);

                              // Step 3: Compute phi(n) = (p-1) * (q-1)
                             BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));


                             // Step 4: Select a small odd integer e that is relatively prime to phi(n).
                             // By convention the prime 65537 is used as the public exponent.
                             e = new BigInteger ("65537");

                             // Step 5: Compute d as the multiplicative inverse of e modulo phi(n).
                             d = e.modInverse(phi);
                            

                        int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Waiting for spies to visit...");
                        while(true) {
				Socket clientSocket = listenSocket.accept();
                                counter++;
				Connection c = new Connection(clientSocket, counter, usernames,locations, map, n,e, d, hp);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
/**
 *  Sets up a connection with the client
 * @author samyak
 */
class Connection extends Thread {
	DataInputStream in;
        //DataInputStream in1;
	DataOutputStream out;
	Socket clientSocket;
        String []usernames = new String[3];
        String []locations = new String[3];
        HashMap <String, String>map;
        HashPassword hp;
        BigInteger n,e,d; 
                             
        int counter =0;
	public Connection (Socket aClientSocket, int counter, String [] usernames, String [] locations, HashMap <String, String>map, BigInteger n, BigInteger e, BigInteger d, HashPassword hp) {
		try {
                        clientSocket = aClientSocket;
                        // initialise the various varibales.
                        this.counter = counter;
                        this.usernames = usernames;
                        this.locations = locations;
                        this.map = map;
                        this.hp = hp;
                        this.n = n;
                        this.e = e;
                        this.d = d;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
                       
			this.start();
		} catch(IOException exp) {System.out.println("Connection:"+exp.getMessage());}
	}
        /**
         * Runs the code every time a connection is established.
         */
	public void run(){
		try {			               			
                           
                            //Generating and sending public key to the spys
                            String publicKey = e + "," +n;
                            out.writeUTF(publicKey);
                            
                            // Decrypting the Symmetric Key
                            String encryptSymmkey = in.readUTF();
                            String decryptSymmKey = decrypt(encryptSymmkey, n, d);
                            
                            // reading the username and password
                            int length1 = in.readInt();
                            byte [] userPass = new byte[length1];
                            in.readFully(userPass);
                        
                             // reading the location
                             int length2 = in.readInt();
                             byte [] ne = new byte[length2];
                             in.readFully(ne);
                        
                            // Decrypting the id and password
                            String usernamePassword = decryptTEA(userPass, decryptSymmKey);
                            String values[] = usernamePassword.split(" ");
                            
                            // Decrypting the location 
                            String orgmessage = decryptTEA(ne, decryptSymmKey);
                            
                            // Generating the hash function
                            String passHash = hp.hashingFunc(values[1]+"XYsTrP");
                           
                           // compares the username and password
                           if((values[0].equals("mikem") && map.get("mikem").equals(passHash)) || (values[0].equals("jamesb") && map.get("jamesb").equals(passHash)) || (values[0].equals("joem") && map.get("joem").equals(passHash)))
                            {
                                System.out.println("Got visit "+counter+" from "+values[0]);
                                // writes the kml file everytime the spy is authenticated 
                                FileWrite(values[0],orgmessage);
                                out.writeUTF("Thank you. Your location was securely transmitted to Intelligence Headquarters.");
                            }
                            else
                            {
                                System.out.println("Got visit "+counter+" from "+values[0]+". Illegal Password attempt. This may be an attack.");
                                // writes the default file when the spy connection is illegal
                                FileWrite(usernames,locations);
                                out.writeUTF("Not a valid user-id or password.");
                            }
                }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
                }catch(RuntimeException e){System.out.println(e.getMessage());
                    try {
                        clientSocket.close();
                        System.exit(0);
                    } catch (IOException ex) {
                        
                    }
		} catch (NoSuchAlgorithmException ex) {
                System.out.println("Hashing of password went bad");
            } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
        /**
         * Decrypts the message using server's private key using RSA algorithm.
         * @param encrpytmessage the message that needs to be decrypted (symmetric key)
         * @param n the modulus operator, part of the private key
         * @param d the exponent operator, part of the private key
         * @return the decrypted symmetric key as a string
         */
        public String decrypt(String encrpytmessage, BigInteger n, BigInteger d)
        {
            BigInteger enmessage = new BigInteger(encrpytmessage);
            BigInteger clear = enmessage.modPow(d, n); // Decrypt, clear is the resulting clear text
            String clearStr = new String(clear.toByteArray());  // Decode to a string
            return clearStr;
        
        }
        
        /**
         * Writes the kml file with the information provided from the spy
         * @param name the username of the spy
         * @param location the location of the spy
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
        /**
         * Writes the kml file with default values, or when the spy is not properly authenticated.
         * @param usernames the list of existing spy usernames
         * @param locations the list of existing spy locations 
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
        /**
         * Decrpyts the username, password and location using TEA and symmetric Key
         * @param encrpytmessage the message to be decrypted 
         * @param symmetrickey the key used for decryption 
         * @return the decrypted message as a string.
         */
         public String decryptTEA(byte[] encrpytmessage, String symmetrickey)
        {
            byte[] originalSymm = symmetrickey.getBytes();
            Tea t = new Tea(originalSymm);
            byte[] decrypt = t.decrypt(encrpytmessage);
            String decryptMessage = new String(decrypt);
            return decryptMessage;
        }
}