/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import Ramki.RamkaGlowna;
import java.awt.image.BufferedImage;

/**
 *
 * @author Darek Xperia
 */
public class Kula extends Obiekt {

    public BufferedImage[] kulaSpr;
    public Punkt wektorRuchu;
    //private int zwolnienieAnimacji;
    private double katX, katY;
    private int maxX, maxY;
    private double posXd, posYd;
    private double predkosc;
    private long czasDoPrzyspieszenia;
    private final double su = 0.05;
    private long tmpCzas;

    public Kula() {
        super();
        dodatek();

    }

    public Kula(int x, int y) {
        super(x, y);
        posXd = x;
        posYd = y;
        dodatek();
    }

    public Kula(double x, double y) {
        super((int) x, (int) y);
        posXd = x;
        posYd = y;
        dodatek();
    }

    private void dodatek() {
        kulaSpr = MenadzerTekstur.getTekstura("kula_full1");
        maxX = 36;
        maxY = 18;
        ruch = new Animacja(this, kulaSpr, 1);
        ruch.obecnaKlatka = ruch.calkowitaIloscKlatek / 2;
        ruch.stop();
        wektorRuchu = new Punkt(1, 2); // stój w miejscu
        katX = 0;
        katY = 0;
        //zwolnienieAnimacji = 0;
        predkosc = 2;
        czasDoPrzyspieszenia = 1000 * 10; // szybciej co 10 sek
        tmpCzas = System.currentTimeMillis();
    }

    public double getPredkosc() {
        return predkosc;
    }
    
    public void reset() {
        ruch.obecnaKlatka = ruch.calkowitaIloscKlatek / 2;
        wektorRuchu = new Punkt(1, 2); // stój w miejscu
        katX = 0;
        katY = 0;
        //zwolnienieAnimacji = 0;
        predkosc = 1.5;
        czasDoPrzyspieszenia = 1000 * 10; // szybciej co 10 sek
        tmpCzas = System.currentTimeMillis();
        posXd=500; 
        posYd=500;
        posX=500;
        posY=500;
    }

    public void zmienPozycje() {
        if (Gra.zapauzowana == true) {
            tmpCzas = System.currentTimeMillis();
            return;
        } // pauza
        if (predkosc < 4.5) { // maksymalna predkosc
            czasDoPrzyspieszenia -= (System.currentTimeMillis() - tmpCzas);
            tmpCzas = System.currentTimeMillis();
            if (czasDoPrzyspieszenia < 0) {
                // przyspieszenie
                predkosc += su;
                czasDoPrzyspieszenia = 1000 * 10; // szybciej co 10 sek
            }
        }
        Punkt pozycja = new Punkt(posXd, posYd);
        pozycja.translate(wektorRuchu.x * predkosc, wektorRuchu.y * predkosc);
        Punkt tmpWektorRuchu = new Punkt(wektorRuchu.x, wektorRuchu.y);

        boolean odbicie = false;

        if (pozycja.x < Rozne.kontener.getX()) { // lewa strona planszy
            odbicie = true;
            pozycja.x = Rozne.kontener.getX();
            //odwróć kierunek w poziomie
            tmpWektorRuchu.x = -tmpWektorRuchu.x;
        } else if ((pozycja.x + getzajmowanaPowierzchnia().width) > Rozne.kontener.getWidth() + Rozne.kontener.getX()) { // prawa strona planszy
            odbicie = true;
            pozycja.x = Rozne.kontener.getWidth() + Rozne.kontener.getX() - getzajmowanaPowierzchnia().width;
            //odwróć kierunek w poziomie
            tmpWektorRuchu.x = -tmpWektorRuchu.x;
        }

        if (pozycja.y < Rozne.kontener.getY()) { // góra planszy
            odbicie = true;
            pozycja.y = Rozne.kontener.getY();
            //odwróć kierunek w poziomie
            tmpWektorRuchu.y = -tmpWektorRuchu.y;
        } else if ((pozycja.y + getzajmowanaPowierzchnia().height) > Rozne.kontener.getHeight() + Rozne.kontener.getY()) { // doł planszy
            if (Gra.getIloscKul()>0)  {
                reset();
                Gra.setIloscKul(Gra.getIloscKul()-1);
                RamkaGlowna.platforma.ustawNaSrodkuX();
                // zapauzuj grę
                Gra.zapauzujGre();
                return;
            } else 
            {
                // game over
                MenuGlowne.wyswietlKoniec(true);
                RamkaGlowna.mO.usunWszystkieCegly();
                Gra.zakonczGre();
            }
            //odbicie = true;
            //pozycja.y = Rozne.kontener.getHeight() + Rozne.kontener.getY() - getzajmowanaPowierzchnia().height;
            //odwróć kierunek w poziomie
            //tmpWektorRuchu.y = -tmpWektorRuchu.y;
        }

        if (odbicie) {
            wektorRuchu = tmpWektorRuchu;
        }
        posXd = pozycja.x;
        posYd = pozycja.y;
        posX = (int) posXd;
        posY = (int) posYd;
        // ustawiamy klatkę animacji
        /*
        zwolnienieAnimacji++;
        if (zwolnienieAnimacji > 4) {
        float tmp = ruch.obecnaKlatka + (int)((float)wektorRuchu.x/1) - (int)(((float)wektorRuchu.y/2) * 34);
            if (tmp > 0)
                ruch.obecnaKlatka =  Math.abs(tmp) > (ruch.calkowitaIloscKlatek-1) ? Math.abs(tmp) % (ruch.calkowitaIloscKlatek-1) : Math.abs(tmp);
            else
                ruch.obecnaKlatka =  tmp < 0 ? (ruch.calkowitaIloscKlatek-1) - Math.abs(tmp % (ruch.calkowitaIloscKlatek-1)) : (ruch.calkowitaIloscKlatek-1) - Math.abs(tmp);
            zwolnienieAnimacji = 0;
        }
         */
        // inne podejscie - ciagle nierawidłowe - ToDo

        katX += wektorRuchu.x;
        katY += wektorRuchu.y;
        katX = katX >= 360 ? katX - 360 : katX < 0 ? 360 + katX : katX;
        katY = katY >= 360 ? katY - 360 : katY < 0 ? 360 + katY : katY;

        ruch.obecnaKlatka = (int) (Math.floor((katX * (maxX - 1)) / 360) + Math.floor((katY * (maxY - 1)) / 360) * maxX);

    }

    public boolean sprawdzKolizje(Obiekt obiekt) {
        if (obiekt != this && obiekt.powodujeKolizje) {
            return getzajmowanaPowierzchnia().intersects(obiekt.getzajmowanaPowierzchnia());
        }
        return false;
    }

}
