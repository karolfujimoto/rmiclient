/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.up.karol.client;

import br.edu.up.karol.server.InterfaceCalculator;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karol
 */
public class CalculatorClient {
    public static void main(String[] args) { 
        Integer n, t, i;
        Thread th = new Thread();
        LocalCalculator lc = new LocalCalculator();
        
        n = 500000000;
        t = 4;
        
        for(i = 0;i<=n;i+=n/t) {
            lc = new LocalCalculator(i, i+n/t);
            th = new Thread(lc);
            th.start();
            try {
                th.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(CalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Double x = lc.getResult();
        
        System.out.println(x*4);
        try {
            InterfaceCalculator c = (InterfaceCalculator)
                           Naming.lookup(
                 "rmi://localhost/CalculatorService");
            System.out.println( 4*c.calculate(0,n,t) ); 
        } catch (NotBoundException ex) {
            Logger.getLogger(CalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        }

      
   }
}
