/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms;
import java.lang.Thread;

/**
 *
 * @author Sinjun Strydom
 */
public class PrimeRun implements Runnable {
    
    String minPrime;
    
    PrimeRun(String minPrime){
        this.minPrime = minPrime;
        
        System.out.println(minPrime);
    }
    
    @Override
    public void run(){
        
    }
}
