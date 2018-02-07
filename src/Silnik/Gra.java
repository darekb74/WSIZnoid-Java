/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import Ramki.RamkaGlowna;

/**
 *
 * @author Darek Xperia
 */
public class Gra {
    
    protected static boolean wTrakcie = false;
    protected static boolean zapauzowana = false;
    protected static int iloscKul;
    protected static int obecnyPoziom;
   

    public static void reset () {
        wTrakcie = false;
        obecnyPoziom = 0;
        //zapauzowana = true;
        setIloscKul(0);
        Wynik.setWynik(0);
        //Uklad.wyswietlUklad(5);
    }
    
    public static void rozpocznijGre() {
        obecnyPoziom = 0;
        Uklad.wyswietlUklad(obecnyPoziom);
        MenuGlowne.wyswietlKoniec(false);
        wTrakcie = true;
        setIloscKul(3);
        Wynik.setWynik(0);
        Wynik.dodaj(0);
        RamkaGlowna.platforma.ustawNaSrodkuX();
        RamkaGlowna.kula.reset();
    }

    public static boolean iswTrakcie() {
        return wTrakcie;
    }

    public static boolean isZapauzowana() {
        return zapauzowana;
    }
    
    public static void zakonczGre() {
        wTrakcie = false;
        //zapauzowana = true;
    }
    
    public static void zapauzujGre() {
        zapauzowana = true;
    }
    
    public static void odpauzujGre() {
        zapauzowana = false;
    }

    public static int getIloscKul() {
        return iloscKul;
    }

    public static void setIloscKul(int iloscKul) {
        Gra.iloscKul = iloscKul;
        Wynik.setPilki(iloscKul);
    }
    
    
}
