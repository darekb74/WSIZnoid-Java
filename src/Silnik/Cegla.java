/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Ramki.RamkaGlowna;
import java.util.Random;

/**
 *
 * @author Darek Xperia
 */
public class Cegla extends Obiekt {

    private BufferedImage[] ceglaSpr;
    protected ArrayList<Efekt> obiektyPowiazane = new ArrayList<>();
    protected Point matryca;
    private boolean szklo_zbite = false;
    private Random rnd = new Random();

    public Cegla() {
        super();
        dodatek();
    }

    public Cegla(int x, int y) {
        super(x, y);
        dodatek();
    }

    public Cegla(int x, int y, int typ) {
        super(x, y);
        this.typ = typ;
        dodatek();
    }

    public void dodajObiektPowiazany(int typ) {
        Efekt tmp;
        switch (typ) {
            case 1: //dym
                tmp = new Efekt(1, 1, 5);
                tmp.ustawObiektOdniesienia(this);
                tmp.setzBuff(2);
                RamkaGlowna.mO.dodajObiekt(tmp);
                obiektyPowiazane.add(tmp);
                break;
            case 2: //eksplozja
                tmp = new Efekt(1, 1, 6);
                tmp.ustawObiektOdniesienia(this);
                tmp.setzBuff(2);
                RamkaGlowna.mO.dodajObiekt(tmp);
                obiektyPowiazane.add(tmp);
                break;
            case 3: //tekstura zbitego szkła
                tmp = new Efekt(posX, posY, 7);
                tmp.ustawObiektOdniesienia(this);
                tmp.setzBuff(2);
                RamkaGlowna.mO.dodajObiekt(tmp);
                obiektyPowiazane.add(tmp);
                break;

        }
    }

    public void usunObiektyPowiazane() {
        for (Efekt ef : obiektyPowiazane) {
            RamkaGlowna.mO.usunObiekt(ef);
        }
        obiektyPowiazane.clear();
    }

    private void dodatek() {
        switch (typ) {
            default:
                ceglaSpr = MenadzerTekstur.getTekstura("emptySpr");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 0:
                ceglaSpr = MenadzerTekstur.getTekstura("biala");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 1:
                ceglaSpr = MenadzerTekstur.getTekstura("pomaranczowa");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 2:
                ceglaSpr = MenadzerTekstur.getTekstura("niebieska");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 3:
                ceglaSpr = MenadzerTekstur.getTekstura("zielona");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 4:
                ceglaSpr = MenadzerTekstur.getTekstura("czerwona");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 5:
                ceglaSpr = MenadzerTekstur.getTekstura("granatowa");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 6:
                ceglaSpr = MenadzerTekstur.getTekstura("rozowa");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 7:
                ceglaSpr = MenadzerTekstur.getTekstura("zolta");
                ruch = new Animacja(this, ceglaSpr, 1);
                ruch.stop();
                break;
            case 8:
                ceglaSpr = MenadzerTekstur.getTekstura("wybuchajaca_a");
                ruch = new Animacja(this, ceglaSpr, 6);
                ruch.start();
                break;
            case 9:
                ceglaSpr = MenadzerTekstur.getTekstura("szara_a");
                ruch = new Animacja(this, ceglaSpr, 4);
                ruch.setOdtwarzajWPetli(false);
                ruch.stop();
                break;
            case 10:
                ceglaSpr = MenadzerTekstur.getTekstura("zielona_a");
                ruch = new Animacja(this, ceglaSpr, 4);
                ruch.setOdtwarzajWPetli(false);
                ruch.stop();
                break;
            case 11:
                ceglaSpr = MenadzerTekstur.getTekstura("czerwona_a");
                ruch = new Animacja(this, ceglaSpr, 4);
                ruch.setOdtwarzajWPetli(false);
                ruch.stop();
                break;
            case 12:
                ceglaSpr = MenadzerTekstur.getTekstura("niebieska_a");
                ruch = new Animacja(this, ceglaSpr, 4);
                ruch.setOdtwarzajWPetli(false);
                ruch.stop();
                break;
            case 13:
                ceglaSpr = MenadzerTekstur.getTekstura("szklana");
                ruch = new Animacja(this, ceglaSpr, 4);
                ruch.setOdtwarzajWPetli(false);
                szklo_zbite = false;
                ruch.stop();
                break;
        }
    }

    public void uderzenie(boolean wymusUsuniecie) {
        if (powodujeKolizje == false) {
            return;
        }
        switch (typ) {
            //default:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                wyswietlany = false;
                ruch.stop();
                powodujeKolizje = false;
                // dodaj punkty
                Wynik.dodaj(5);
                if (!wymusUsuniecie) {
                    Ramki.RamkaGlowna.efekt.odtworzEfekt(3);
                }
                break;
            case 8: // wybuchajaca

                ruch.stop();
                wyswietlany = false;
                powodujeKolizje = false;
                this.obiektyPowiazane.get(0).wyswietlany = false;
                this.obiektyPowiazane.get(0).ruch.stop();
                this.obiektyPowiazane.get(1).ruch.start();
                // dodaj punkty
                Wynik.dodaj(25);
                Ramki.RamkaGlowna.efekt.odtworzEfekt(0);
                // inicjujemy uderzenie otaczających elementów
                // wymuszamy usuniecie
                int g,
                 s,
                 d;
                for (int x = -1; x < 2; x++) {
                    g = matryca.x + x + 20 * (matryca.y - 1);
                    s = matryca.x + x + 20 * matryca.y;
                    d = matryca.x + x + 20 * (matryca.y + 1);
                    if (g >= 0 && g < 300) {
                        if (matryca.x - 1 <= Uklad.ceg[g].matryca.x && Uklad.ceg[g].matryca.x <= matryca.x + 1 && matryca.y - 1 == Uklad.ceg[g].matryca.y) {
                            Uklad.ceg[g].uderzenie(true);
                        }
                    }
                    if (s >= 0 && s < 300 && x != 0) {
                        if (matryca.x - 1 <= Uklad.ceg[s].matryca.x && Uklad.ceg[s].matryca.x <= matryca.x + 1 && matryca.y == Uklad.ceg[s].matryca.y) {
                            Uklad.ceg[s].uderzenie(true);
                        }
                    }
                    if (d >= 0 && d < 300) {
                        if (matryca.x - 1 <= Uklad.ceg[d].matryca.x && Uklad.ceg[d].matryca.x <= matryca.x + 1 && matryca.y + 1 == Uklad.ceg[d].matryca.y) {
                            Uklad.ceg[d].uderzenie(true);
                        }
                    }
                }
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                if (wymusUsuniecie) {
                    wyswietlany = false;
                    ruch.stop();
                    powodujeKolizje = false;
                    // dodaj punkty
                    Wynik.dodaj(35);
                } else {
                    ruch.start();
                    Ramki.RamkaGlowna.efekt.odtworzEfekt(4);
                }
                break;
            case 13:
                if (wymusUsuniecie) {
                    Ramki.RamkaGlowna.efekt.odtworzEfekt(2);
                    this.obiektyPowiazane.get(0).wyswietlany = false;
                    wyswietlany = false;
                    ruch.stop();
                    powodujeKolizje = false;
                    // dodaj punkty
                    Wynik.dodaj(8);
                } else if (szklo_zbite == false) {
                    Ramki.RamkaGlowna.efekt.odtworzEfekt(2);
                    szklo_zbite = true;
                    this.obiektyPowiazane.get(0).wyswietlany = true;
                    ruch.start();
                    Wynik.dodaj(7);
                } else {
                    Ramki.RamkaGlowna.efekt.odtworzEfekt(1);
                    this.obiektyPowiazane.get(0).wyswietlany = false;
                    wyswietlany = false;
                    ruch.stop();
                    powodujeKolizje = false;
                    // dodaj punkty
                    Wynik.dodaj(8);
                }

                break;
        }
    }

}
