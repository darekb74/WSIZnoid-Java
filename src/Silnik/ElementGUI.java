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
public class ElementGUI extends Obiekt {

    private BufferedImage[] oSpr;

    public ElementGUI(int x, int y, String typ) {
        super(x, y);
        dodatek(typ);
    }

    public void UstawTyp(String typ) {
        dodatek(typ);
    }

    private void dodatek(String n) {

        switch (n) {
            case "wynik":
            case "pilki":
            case "najlepszy":
            case "W":
            case "S":
            case "I":
            case "Z":
            case "N":
            case "O":
            case "D":
            case "enter":
            case "spacja":
            case "pauza":
            case "koniec":
                oSpr = MenadzerTekstur.getTekstura(n);
                ruch = new Animacja(this, oSpr, 1);
                ruch.stop();
                this.zBuff = 2; // on top
                break;
            default:
                oSpr = MenadzerTekstur.getTekstura("emptySpr");
                ruch = new Animacja(this, oSpr, 1);
                this.zBuff = 2; // on top
                ruch.stop();
                break;
        }

    }

}
