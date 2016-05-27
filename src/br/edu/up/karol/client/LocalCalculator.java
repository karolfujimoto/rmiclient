/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.up.karol.client;

/**
 *
 * @author karol
 */
public class LocalCalculator implements Runnable {
    private Integer init, end;
    private static double result = 0;
    private static Integer instances;
    
    public LocalCalculator(){}
    public LocalCalculator(Integer init, Integer end) {
        this.init = init;
        this.end = end;   
    }

    @Override
    public synchronized void run() {
        for(int n = init;n <= end;n++)
            result += (Math.pow(-1, n))/((2*n)+1);      
    }
    public Double getResult() {
        return result;
    }
    public Integer getInstances() {
        return instances;
    }
}
