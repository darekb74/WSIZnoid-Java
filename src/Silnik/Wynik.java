/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import Ramki.RamkaGlowna;
import static Ramki.RamkaGlowna.mO;

/**
 *
 * @author Darek Xperia
 */
public class Wynik {

    protected static Liczba[] element = new Liczba[8];
    protected static Liczba[] elementN = new Liczba[8];
    protected static Liczba pilki = new Liczba(134,741);
    protected static long wynik;
    protected static long najlepszy;
    
    protected static ElementGUI nWyn;
    protected static ElementGUI nNWyn;
    protected static ElementGUI nPilki;
    
    public static void setPilki(int p) {
        Wynik.pilki.liczba = p;
    }
    
    public static long getWynik() {
        return wynik;
    }

    public static void setWynik(long wynik) {
        Wynik.wynik = wynik;
    }

    public static long getNajlepszy() {
        return najlepszy;
    }

    public static void reset() {
        for (int n = 0; n < 8; n++) {
            element[7 - n] = new Liczba(150 + 10 + n * 25, 10, 0);
            mO.dodajObiekt(element[7 - n]);
            element[7 - n].setzBuff(2);
        }
        wynik = 0;
        
        // napisy
        nWyn = new ElementGUI(0, 6, "wynik");
        nWyn.powodujeKolizje = false;
        mO.dodajObiekt(nWyn);

        nNWyn = new ElementGUI(570, 9, "najlepszy");
        nNWyn.powodujeKolizje = false;
        mO.dodajObiekt(nNWyn);

        nPilki = new ElementGUI(0, 740, "pilki");
        nPilki.powodujeKolizje = false;
        mO.dodajObiekt(nPilki);

    }
    
    public static void wyswietlElementy() {
        if (Gra.iswTrakcie()) {
            nWyn.wyswietlany(true);
            nPilki.wyswietlany(true);
            pilki.wyswietlany(true);
            for (int n = 0; n < 8; element[n++].wyswietlany(true));
            //setWynik(wynik);
            setPilki(Gra.getIloscKul());
            // dodatkowo wyświetl paletke i piłke
            RamkaGlowna.platforma.wyswietlany(true);
            RamkaGlowna.kula.wyswietlany(true);
            // dysze
            RamkaGlowna.dyszaNm.wyswietlany(true);
        } else {
            nWyn.wyswietlany(false);
            nPilki.wyswietlany(false);
            pilki.wyswietlany(false);
            for (int n = 0; n < 8; element[n++].wyswietlany(false));
            RamkaGlowna.platforma.wyswietlany(false);
            RamkaGlowna.kula.wyswietlany(false);
            // dysze
            RamkaGlowna.dyszaNm.wyswietlany(false);
        }
    }
    
    public static void resetP() {
       pilki.liczba = Gra.getIloscKul();
       mO.dodajObiekt(pilki);
       pilki.setzBuff(2);
    }

    public static void resetN() {
        for (int n = 0; n < 8; n++) {
            elementN[7 - n] = new Liczba(813 + 10 + n * 25, 10, 0);
            mO.dodajObiekt(elementN[7 - n]);
            elementN[7 - n].setzBuff(2);
        }
        // wczytaj najlepszy wynik
        Rozne.wczytajNajlepszy("highscore.dat");
    }

    public static void dodaj(int punkty) {
        wynik += punkty;
        for (int n = 0; n < 8; n++) {
            element[n].liczba = Integer.parseInt(String.format("%08d", wynik).substring(7 - n, 7 - n + 1));
        }
        if (wynik > najlepszy) {
            najlepszyUstaw(wynik);
        }
    }

    public static void najlepszyUstaw(long w) {
        najlepszy = w;
        for (int n = 0; n < 8; n++) {
            elementN[n].liczba = Integer.parseInt(String.format("%08d", najlepszy).substring(7 - n, 7 - n + 1));
        }
    }
}
