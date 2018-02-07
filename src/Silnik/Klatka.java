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
public class Klatka {

    private BufferedImage klatka;
    private int czasTrwania;

    public Klatka(BufferedImage frame, int czasTrwania) {
        this.klatka = frame;
        this.czasTrwania = czasTrwania;
    }

    public BufferedImage getKlatka() {
        return klatka;
    }

    public void setKlatka(BufferedImage klatka) {
        this.klatka = klatka;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }
}
