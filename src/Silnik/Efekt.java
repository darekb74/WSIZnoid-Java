/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Darek Xperia
 */
public class Efekt extends Obiekt {

    public BufferedImage[] efektSpr;
    protected Obiekt obiektOdniesienia;

    public Efekt() {
        super();
        dodatek();
    }

    public Efekt(int x, int y) {
        super(x, y);
        dodatek();
    }

    public Efekt(int x, int y, int typEfektu) {
        super(x, y);
        dodatek();
        this.typ = typEfektu;
        setTypEfektu(typEfektu);
    }

    private void dodatek() {
        typ = 0;
        setTypEfektu(typ);
    }

    public void setTypEfektu(int typEfektu) {
        Random g = new Random();
        switch (typEfektu) {
            case 0:
                efektSpr =  MenadzerTekstur.getTekstura("emptySpr");
                ruch = new Animacja(this, efektSpr, 1);
                this.typ = typEfektu;
                ruch.stop();
                break;
            case 1:
                efektSpr = MenadzerTekstur.getTekstura("plasma");
                ruch = new Animacja(this, efektSpr, 6);
                this.typ = typEfektu;
                ruch.start();
                break;
            case 2:
                efektSpr = MenadzerTekstur.getTekstura("silnik_maly_niebieski");
                ruch = new Animacja(this, efektSpr, 1);
                ruch.setKierunekAnimacji(6);
                this.typ = typEfektu;
                ruch.start();
                break;
            case 3:
                efektSpr = MenadzerTekstur.getTekstura("silnik_duzy");
                ruch = new Animacja(this, efektSpr, 1);
                this.typ = typEfektu;
                ruch.setKierunekAnimacji(6);
                ruch.start();
                break;
            case 4:
                efektSpr = MenadzerTekstur.getTekstura("planeta");
                ruch = new Animacja(this, efektSpr, 6);
                this.typ = typEfektu;
                ruch.start();
                break;
            case 5:
                efektSpr = MenadzerTekstur.getTekstura("dym");
                ruch = new Animacja(this, efektSpr, 6);
                this.typ = typEfektu;
                // ustawianie losowej klatki początkowej
                ruch.obecnaKlatka = g.nextInt(ruch.calkowitaIloscKlatek-1);
                ruch.start();
                break;
            case 6:
                efektSpr = MenadzerTekstur.getTekstura("eksplozja");
                ruch = new Animacja(this, efektSpr, 2);
                ruch.setKierunekAnimacji(4);
                ruch.setOdtwarzajWPetli(false);
                this.typ = typEfektu;
                ruch.stop();
                break;
            case 7:
                Random tmp = new Random();
                //efektSpr = MenadzerTekstur.getTekstura("pekniecie" + (tmp.nextInt(4)+1));
                efektSpr = MenadzerTekstur.getTekstura("pekniecie" + (3));
                ruch = new Animacja(this, efektSpr, 1);
                ruch.setOdtwarzajWPetli(false);
                this.typ = typEfektu;
                this.wyswietlany = false;
                ruch.stop();
                break;
        }
    }

    public void ustawObiektOdniesienia(Obiekt odnosnik) {
        obiektOdniesienia = odnosnik;
    }

    public void specjalneAkcje(int idAkcji) {
        switch (idAkcji) {
            case 0:
                // dysza platformy - mała, niebieska, lewa
                posX = obiektOdniesienia.posX + 24;
                posY = obiektOdniesienia.posY + 20;
                break;
            case 1:
                // dysza platformy - mała, niebieska, prawa
                posX = obiektOdniesienia.posX + 88;
                posY = obiektOdniesienia.posY + 20;
                break;
            case 2:
                // dysza platformy - duża, zwykła, prawa
                posX = obiektOdniesienia.posX + 8;
                posY = obiektOdniesienia.posY + 20;
                break;
            case 3:
                // dysza platformy - duża, zwykła, prawa
                posX = obiektOdniesienia.posX + 72;
                posY = obiektOdniesienia.posY + 20;
                break;
            case 4:
                posX = obiektOdniesienia.posX-10;
                posY = obiektOdniesienia.posY-36;
                break;
            case 5:
                posX = obiektOdniesienia.posX-44;
                posY = obiektOdniesienia.posY-52;
                break;
        }
    }

}
