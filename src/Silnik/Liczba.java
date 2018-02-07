/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.image.BufferedImage;

/**
 *
 * @author Darek Xperia
 */
public class Liczba extends Obiekt{
    
    private BufferedImage[] liczbySpr;
    public int knl = 5;
    public int liczba;
    
    public Liczba() {
        super();
        dodatek();
    }
    
    public Liczba(int x, int y) {
        super(x,y);
        dodatek();
    }
    
    public Liczba(int x, int y, int wartosc) {
        super(x,y);
        liczba = wartosc;
        dodatek();
    }
    
    private void dodatek () {
        liczbySpr = MenadzerTekstur.getTekstura("liczby2");
        ruch = new Animacja(this, liczbySpr, 2);
        //ruch.obecnaKlatka = typ*knl;
        ruch.start();
        powodujeKolizje = false;
    }
}
