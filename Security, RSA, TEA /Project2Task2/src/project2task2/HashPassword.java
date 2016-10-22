/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2task2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Creates a hash on the password
 * @author Samyak
 */
public class HashPassword {
    /**
     * Initializes the usernames and passwords as a Hashmap.
     * @return the Hashmaps of usernames and passwords 
     */
    public  HashMap<String, String> initializePassword()
    {
    //Storing hashing table of passwords 
                        HashMap<String, String> map = new HashMap<String, String>();
                        String pairs[][] = {{"jamesb", "233d5924d6da252d8db5c1b1529ddd75"},
                                {"mikem","fc79cbfd63c97e0c65a938432a1eede5"},
                                {"joem", "a44be156676bb7c2af374b45a1281862"}};
                        for (String[] pair : pairs) {
                                map.put(pair[0], pair[1]);
                        }
    return map;
    }
    /**
     * Hashes the password sent by the spy(client).
     * @param pass the password parameter
     * @return the hash value of the password as a string
     * @throws NoSuchAlgorithmException 
     */
    public String hashingFunc(String pass) throws NoSuchAlgorithmException
        {
            MessageDigest m;
            // Creating an byte array as the return value of update is a byte array
            byte[] digest = null;
            
            // Creates an instance of the MD5
            m = MessageDigest.getInstance("MD5");
            m.reset();
            
            // Updates the text to MD5
            m.update(pass.getBytes(), 0, pass.length());
            String md5 = new BigInteger(1, m.digest()).toString(16);
            
            return(md5);
       
        }
}
