/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2task2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

/**
 * It is the client that runs the client side logic of encryption using RSA and TEA algorithms.
 * @author samyak
 * @version 1.0
 */
public class TCPSpyUsingTEAandPasswordsAndRSA {
	public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try{
			
                        Scanner sc = new Scanner(System.in);
                        
                        // Asking the client for user id, password and locations.
                        System.out.println("Enter your id");
                        String id = sc.nextLine();
                        System.out.println("Enter your password");
                        String password = sc.nextLine();
                        System.out.println("Enter Location");
                        String location = sc.nextLine();
                        
                        // A string containing the id and password
                        String fullmessage = id+ " "+ password;
                        
                        // Server port is set up and creates a socket.
                        int serverPort = 7896;
			s = new Socket((args[0]), serverPort); 
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
                        
                        // Generating a random symmetric Key
                        Random rnd = new Random();
                        BigInteger Key = new BigInteger(16*8,rnd);
                        String symmKey = Key+"";
                        
                        // Recieving commanders public key
                        String pubKey = in.readUTF();
                        
                        // Encrypting the Symmetric Key 
                        BigInteger symmkeyEncrypted = encrypt(symmKey, pubKey);
                        
                        // Sending the encrypted symmetric key to the server.
                        String enSymmetricKey = symmkeyEncrypted + "";
                        out.writeUTF(enSymmetricKey);
                        
                        //Encrypting Username and Password with the given SymmetricKey 
                        byte [] encryptUserPass = encryptTEA(fullmessage, symmKey);
                        
                        // Encrypting the location
                        byte [] encryptlocation = encryptTEA(location, symmKey);
                        
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
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
        /**
         * Encrypts the symmetric key using RSA
         * @param location is actually the symmetric key used TEA
         * @param pubKey the RSA public key.
         * @return the encrypted symmetric key
         */
        public static BigInteger encrypt(String location, String pubKey)
        {
            String publicKeys[] = pubKey.split(",");
            BigInteger e = new BigInteger(publicKeys[0]); 
            BigInteger n = new BigInteger(publicKeys[1]);
                        
            BigInteger m = new BigInteger(location.getBytes()); // m is the original clear text
            BigInteger c = m.modPow(e, n);
                        
         return c;
        }
        /**
         * Encrypts the username, password and location using the symmetric key and TEA algorithm.
         * @param message is the message to be encrypted 
         * @param symmetrickey is the 16 byte key for encryption.
         * @return the encrypted message
         */
        public static byte[] encryptTEA(String message, String symmetrickey)
        {
            byte[] originalSymm = symmetrickey.getBytes();
            Tea t = new Tea(originalSymm);
            byte[] originalMessage = message.getBytes();
            byte[] crypt = t.encrypt(originalMessage);
            return crypt ;
	}
         

}