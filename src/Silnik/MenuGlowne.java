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
public class MenuGlowne {

    private static ElementGUI[] lit = new ElementGUI[8];
    //private static int[] lyPos = {0,0,0,0,0,0,0,0};
    private static double lKat = 0;
    //public static boolean wyswietSPACE = false;
    //public static boolean wyswietENTER = false;
    private static ElementGUI ePauza;
    private static ElementGUI eSpacja;
    private static ElementGUI eEnter;
    private static ElementGUI eKoniec;
    
    public static void wyswietlKoniec(boolean w) {
        eKoniec.wyswietlany(w);
    }

    public static void LogoInit() {
        int x = 160;
        lit[0] = new ElementGUI(95 + x, 200, "W");
        lit[1] = new ElementGUI(165 + x, 200, "S");
        lit[2] = new ElementGUI(245 + x, 200, "I");
        lit[3] = new ElementGUI(270 + x, 200, "Z");
        lit[4] = new ElementGUI(342 + x, 200, "N");
        lit[5] = new ElementGUI(420 + x, 200, "O");
        lit[6] = new ElementGUI(530 + x, 200, "I");
        lit[7] = new ElementGUI(555 + x, 200, "D");
        RamkaGlowna.mO.dodajObiekt(lit[0]);
        RamkaGlowna.mO.dodajObiekt(lit[1]);
        RamkaGlowna.mO.dodajObiekt(lit[2]);
        RamkaGlowna.mO.dodajObiekt(lit[3]);
        RamkaGlowna.mO.dodajObiekt(lit[4]);
        RamkaGlowna.mO.dodajObiekt(lit[5]);
        RamkaGlowna.mO.dodajObiekt(lit[6]);
        RamkaGlowna.mO.dodajObiekt(lit[7]);

        ePauza = new ElementGUI(470, 470, "pauza");
        RamkaGlowna.mO.dodajObiekt(ePauza);
        ePauza.wyswietlany(Gra.isZapauzowana());
        eSpacja = new ElementGUI(300, 370, "spacja");
        RamkaGlowna.mO.dodajObiekt(eSpacja);
        eSpacja.wyswietlany(Gra.isZapauzowana());
        eEnter = new ElementGUI(290, 370, "enter");
        RamkaGlowna.mO.dodajObiekt(eEnter);
        eEnter.wyswietlany(Gra.iswTrakcie());
        eKoniec = new ElementGUI(290, 470, "koniec");
        RamkaGlowna.mO.dodajObiekt(eKoniec);
        eKoniec.wyswietlany(false);
        
    }

    public static void RuszajLogo() {
        if (!Gra.iswTrakcie()) {
            for (int i = 0; i < lit.length; i++) {
                lit[i].posY = 200 + (int) (Math.sin(lKat + ((2 * Math.PI) / 8) * i) * 20);
                //lit[i].skalaX = 0.5f + (float)Math.cos(lKat+((2*Math.PI)/8)*i)/4;
                // lit[i].skalaY = 0.5f + (float)Math.cos(lKat+((2*Math.PI)/8)*i)/4;
                lit[i].wyswietlany(true);
            }
        } else {
            for (int i = 0; i < lit.length; lit[i++].wyswietlany(false));
        }

        if (Gra.isZapauzowana()) {
            // ePauza
            ePauza.skalaX = 1 + 0.2f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 3.14);
            ePauza.skalaY = 1 + 0.2f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 3.14);
            ePauza.posX = 510 - (int) (ePauza.ruch.getSprite().getWidth() * ePauza.skalaX) / 2;
            ePauza.wyswietlany(true);

            // eSpacja
            eSpacja.skalaX = 1 + 0.1f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 1.14);
            eSpacja.skalaY = 1 + 0.1f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 1.14);
            eSpacja.posX = 512 - (int) (eSpacja.ruch.getSprite().getWidth() * eSpacja.skalaX) / 2;
            eSpacja.wyswietlany(true);
        } else {
            ePauza.wyswietlany(false);
            eSpacja.wyswietlany(false);
        }
        if (!Gra.iswTrakcie()) {
            // eEnter
            eEnter.skalaX = 1 + 0.1f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 1.14);
            eEnter.skalaY = 1 + 0.1f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 1.14);
            eEnter.posX = 512 - (int) (eEnter.ruch.getSprite().getWidth() * eEnter.skalaX) / 2;
            eEnter.wyswietlany(true);
        } else {
            eEnter.wyswietlany(false);
        }
        
        eKoniec.skalaX = 1 + 0.1f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 4.34);
        eKoniec.skalaY = 1 + 0.1f * (float) Math.cos(lKat + ((2 * Math.PI) / 8) * 4.34);
        eKoniec.posX = 512 - (int) (eKoniec.ruch.getSprite().getWidth() * eKoniec.skalaX) / 2;
        
        lKat += 0.05;
        if (lKat > 6.28) {
            lKat = 0;
        }

    }

}
