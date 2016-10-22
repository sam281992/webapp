/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerPackage;

/**
 * checks for the clicks on a radio button, the number of times 
 * @author APPLE
 * @version 1.1
 */
public class clickModel {
    //sets counter
    int counter[] = new int[4];
    /**
     * checks for the counts in the radio button.
     * @param radio the value of the radio button
     * @return the value of the counter
     */
    
    public int[] count(String radio) {
        // check if the value of the radio button selected is A, increments its counter
        if(radio.equals("A"))
        {
            counter[0] +=1;
        }
        // check if the value of the radio button selected is B, increments its counter
        else if(radio.equals("B"))
        {
            counter[1] +=1;
        }
        // check if the value of the radio button selected is C, increments its counter
        else if(radio.equals("C"))
        {
            counter[2] +=1;
        }
        // check if the value of the radio button selected is D, increments its counter
        else if(radio.equals("D"))
        {
            counter[3] +=1;
        }
     return counter;   
    }
    
}
