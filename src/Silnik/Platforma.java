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
public class Platforma extends Obiekt {

    public BufferedImage[] platformaSpr;

    public Animacja platformaAnim;

    public Platforma() {
        super();
        dodatkowo();
    }

    public Platforma(int x, int y) {
        super(x, y);
        dodatkowo();
    }

    private void dodatkowo() {
        platformaSpr = MenadzerTekstur.getTekstura("platforma");
        ruch = new Animacja(this, platformaSpr, 4);
        ruch.start();
        powodujeKolizje = true;
    }

    @Override
    public void zmienKoordynaty() {
        super.zmienKoordynaty();
        // ogranicznie ruchu do wielkości planaszy
        if (Rozne.kontener == null) {
            return;
        }
        if ((posX + ruch.getSprite().getWidth()) >= Rozne.kontener.getWidth()) {
            posX = Rozne.kontener.getWidth() - ruch.getSprite().getWidth();
        } else if (posX < 0) {
            posX = 0;
        }
        // nie pozwalaj na ruch w czasie kolizji z kulą
        if (RamkaGlowna.kula.sprawdzKolizje(this)) {
            posX -= movX; // zapobiega problemom z przenikaniem kuli i paletki
        }
    }

    public void ustawNaSrodkuX() {
        if (Rozne.kontener == null) {
            return;
        }
        posX = (Rozne.kontener.getWidth() - ruch.getSprite().getWidth()) / 2;
    }
}
