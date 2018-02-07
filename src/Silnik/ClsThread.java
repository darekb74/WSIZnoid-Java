/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Component;

/**
 *
 * @author Darek Xperia
 */
public class ClsThread implements Runnable {

    private Thread watek;
    private int opoznienie = 10;
    private Component komponent;

    public ClsThread(Component k) {
        this.komponent = k;
        threadStart();
    }

    private void threadStart() {
        //Utwórz i uruchom nowy wątek
        watek = new Thread(this);
        watek.start();
    }
    
    public void run() {

        long czas = System.currentTimeMillis();
        while (true) {//niekończąca się pętla
            try {
                if(komponent != null && komponent.getGraphics() != null) komponent.update(komponent.getGraphics());
            //component.repaint();
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println("ClsThread.run:"+e); 
            }
            try {
                czas += opoznienie;
                watek.sleep(Math.max(0, czas - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                System.out.println("ClsThread.run:"+e);
            }
        }
    }
}
