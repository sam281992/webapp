/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2task1;

/**
 * The TCPSpyUSingTEAandPasswords runs the logic of the client side, including encryption using the Symmetric key.
 * @author Samyak
 * @version 1.0
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPSpyUsingTEAandPasswords {
	public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try{
			
                        Scanner sc = new Scanner(System.in);
                        // Asking for the symmetric key
                        System.out.println("Enter symmetric key for TEA(taking first sixteen)");
                        String symmkey = sc.nextLine();
                        
                        // Asking for the id
                        System.out.println("Enter your id");
                        String id = sc.nextLine();
                        
                        // Asking for the password
                        System.out.println("Enter your password");
                        String password = sc.nextLine();
                        
                        // Asking for the location
                        System.out.println("Enter Location");
                        String location = sc.nextLine();
                        
                        
                        // Encrypting the username and password
                        String fullmessage = id+ " "+ password;
                        byte [] encryptUserPass = encrypt(fullmessage, symmkey);
                        
                        // Encrypting the location
                        byte [] encryptlocation = encrypt(location, symmkey);
                        
                        // server port setup, and creates a connection to the server.
                        int serverPort = 7896;
			s = new Socket((args[0]), serverPort); 
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
                      
                        
                        // Sending username and Password
                        out.writeInt(encryptUserPass.length);
                        if(encryptUserPass.length>0)
                            out.write(encryptUserPass,0,encryptUserPass.length);
                        
                        //Sending location 
                        out.writeInt(encryptlocation.length);
                        if(encryptlocation.length>0)
                            out.write(encryptlocation,0,encryptlocation.length);

                        // read a line of data from the stream
			String data = in.readUTF();	    
			System.out.println(data); 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
                }catch (RuntimeException e){ System.out.println("Enter key upto 16 characters");
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
        
        /**
         * Encrypts the message using TEA and the symmetric key.
         * @param message the message to be encrypted
         * @param symmetrickey the key used on encryption.
         * @return the encrypted message as a byte array.
         */
        public static byte[] encrypt(String message, String symmetrickey)
        {
            byte[] originalSymm = symmetrickey.getBytes();
            Tea t = new Tea(originalSymm);
            byte[] originalMessage = message.getBytes();
            byte[] crypt = t.encrypt(originalMessage);
            return crypt ;
	}

}