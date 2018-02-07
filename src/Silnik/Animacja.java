/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darek Xperia
 */
public class Animacja {

    private int czasDoZmiany;                 // Counts ticks for change
    private int opoznienieKlatki;                 // frame delay 1-12 (You will have to play around with this)
    public float obecnaKlatka;               // animations current frame
    private float kierunekAnimacji;         // animation direction (i.e counting forward or backward)
    protected int calkowitaIloscKlatek;                // total amount of klatki for your animation
    protected boolean odtwarzajWPetli;

    private boolean stopped;                // has animations stopped

    private Obiekt obiekt;

    private List<Klatka> klatki = new ArrayList<>();    // Arraylist of klatki 

    public Animacja(Obiekt rodzic, BufferedImage[] klatki, int opoznienie) {
        this.opoznienieKlatki = opoznienie;
        this.stopped = true;
        this.obiekt = rodzic;

        for (BufferedImage klatka : klatki) {
            dodajKlatke(klatka, opoznienie);
        }

        this.czasDoZmiany = 0;
        this.opoznienieKlatki = opoznienie;
        this.obecnaKlatka = 0;
        this.kierunekAnimacji = 1;
        this.calkowitaIloscKlatek = this.klatki.size();
        this.odtwarzajWPetli = true;
    }

    public boolean isOdtwarzajWPetli() {
        return odtwarzajWPetli;
    }

    public void setOdtwarzajWPetli(boolean odtwarzajWPetli) {
        this.odtwarzajWPetli = odtwarzajWPetli;
    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (klatki.isEmpty()) {
            return;
        }
        stopped = false;
    }

    public void stop() {
        if (klatki.isEmpty()) {
            return;
        }
        stopped = true;
    }

    public void restart() {
        if (klatki.isEmpty()) {
            return;
        }
        stopped = false;
        obecnaKlatka = 0;
    }

    public void reset() {
        this.stopped = true;
        this.czasDoZmiany = 0;
        this.obecnaKlatka = 0;
    }

    private void dodajKlatke(BufferedImage klatka, int czasTrwania) {
        if (czasTrwania <= 0) {
            System.err.println("Nieprawidłowy czas wyświetlania klatki: " + czasTrwania);
            throw new RuntimeException("Nieprawidłowy czas wyświetlania klatki: " + czasTrwania);
        }

        klatki.add(new Klatka(klatka, czasTrwania));
        obecnaKlatka = 0;
    }

    public BufferedImage getSprite() {
        return klatki.get((int) obecnaKlatka).getKlatka();
    }

    public void setKierunekAnimacji(float kierunekAnimacji) {
        this.kierunekAnimacji = kierunekAnimacji;
    }

    public void zmienKierunek(boolean doPrzodu) {
        kierunekAnimacji = doPrzodu ? abs(kierunekAnimacji) : -abs(kierunekAnimacji);
    }

    public void update() {
        // specjalne ustawienie dla liczby
        // wznów animację jesli inna liczba
        if (obiekt instanceof Liczba) {
            if (obecnaKlatka != ((Liczba)obiekt).liczba * ((Liczba)obiekt).knl) {
                stopped = false;
            }
        }
        if (!stopped) {
            czasDoZmiany++;
            //zmienKordynaty();
            obiekt.zmienKoordynaty();
            if (czasDoZmiany > opoznienieKlatki) {
                czasDoZmiany = 0;
                // specjalne ustawienie dla liczby
                // zatrzymaj animację, jesli prawidłow liczba
                if (obiekt instanceof Liczba) {
                    if (obecnaKlatka == ((Liczba)obiekt).liczba * ((Liczba)obiekt).knl) {
                        stopped = true;
                        return;
                    }
                }
                obecnaKlatka += kierunekAnimacji;

                if (obecnaKlatka > calkowitaIloscKlatek - 1) {
                    if (odtwarzajWPetli) {
                        obecnaKlatka = 0;
                    } else {
                        obecnaKlatka = 0;
                        stopped = true;
                    }
                } else if (obecnaKlatka < 0) {
                    if (odtwarzajWPetli) {
                        obecnaKlatka = calkowitaIloscKlatek - 1;
                    } else {
                        obecnaKlatka = calkowitaIloscKlatek - 1;
                        stopped = true;
                    }
                }
            }
        }
    }
}
