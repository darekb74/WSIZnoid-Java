/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import Ramki.RamkaGlowna;
import static Silnik.Uklad.ceg;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Darek Xperia
 */
public class MenadzerObiektow {

    // lista obiektów
    private ArrayList<Obiekt> mO = new ArrayList<>();
    // niewidoczny obszar do rysowania - zapobiega miganiu
    protected Image offscreenImage;
    //Czas so wykonanania dodatkowej animacji cegieł
    protected long czasDoAkcji;
    // liczniki do aktywacji
    protected long[] liczniki = new long[300];
    
    private boolean odbitaOP = false;

    // tymczasowe
    public static int x1, x2, y1, y2;

    public MenadzerObiektow() {
        // wczytuje grafikę tła
        offscreenImage = Rozne.kontener.createImage(Rozne.kontener.getSize().width, Rozne.kontener.getSize().height);
        czasDoAkcji = System.currentTimeMillis() + 10; // 10 sekund
    }

    private void sprawdzCzas() {
        long tmpTime = System.currentTimeMillis();
        if (czasDoAkcji <= tmpTime) {
            // pora wykonać akcję
            // najpierw ustawiamy czas do następnej akcji
            czasDoAkcji = tmpTime + 45000; // 45 sek
            // zerujemy akcje
            for (int i = 0; i < liczniki.length; liczniki[i++] = 0);
            // ustawimy zgodnie z funkcja y=x
            // odstep 0.2 sek = 200 ms
            for (int x = 19; x >= 0; x--) { // 20 kolumn
                for (int y = 14; y >= 0; y--) { // 15 rzędów
                    liczniki[x + y * 20] = tmpTime + (875 - (x * 25) - (y * 25));
                }
            }
        }
        // sprawdź, czy nie rozpoczyna się jakaś akcja
        for (int i = 0;
                i < liczniki.length;
                i++) {
            if (liczniki[i] <= tmpTime && liczniki[i] != 0) {
                liczniki[i] = 0;
                if (Gra.iswTrakcie()) {
                    if (ceg[i].typ >= 9 && ceg[i].typ <= 13) {
                        ceg[i].ruch.start();
                    }
                }
            }
        }
    }

    public void dodajObiekt(Obiekt obiekt) {
        mO.add(obiekt);
    }

    public void usunObiekt(Obiekt obiekt) {
        // najpierw usuwamy obiekty powiazane
        if (obiekt instanceof Cegla) {
            if (!((Cegla) obiekt).obiektyPowiazane.isEmpty()) {
                for (Efekt ef : ((Cegla) obiekt).obiektyPowiazane) {
                    mO.remove(ef);
                }
            }
        }
        mO.remove(obiekt);
    }

    private void sprawdzKolizje(Kula kula) {
        if (Gra.isZapauzowana() || !Gra.iswTrakcie()) {
            return;
        }
        // tworzymy listę kolizji
        ArrayList<Cegla> kL = new ArrayList<>();
        //kL.clear();
        for (Obiekt obiekt : (ArrayList<Obiekt>) mO.clone()) { 
            if (obiekt.wyswietlany && obiekt.powodujeKolizje && (obiekt instanceof Cegla || obiekt instanceof Platforma)) {
                if (obiekt instanceof Platforma) {
                    if (kula.sprawdzKolizje(obiekt)) {
                        if (odbitaOP) {
                            return; // zapobiega wielokrotnemu odbiciu przez paletke
                        }
                        odbitaOP = true;
                        x1 = (int) (kula.wektorRuchu.x * kula.getPredkosc() * 10);
                        y1 = (int) (kula.wektorRuchu.y * kula.getPredkosc() * 10);
                        // sprawdzamy, cznie uderazmy z boku
                        Rectangle r = kula.getzajmowanaPowierzchnia().intersection(obiekt.getzajmowanaPowierzchnia());
                        if (r.width < r.height) // uderzenie z boku :(
                        {
                            if (kula.wektorRuchu.x < 0 && Math.abs(r.x - obiekt.posX) < 40
                                    || // lewa stron i w lewo - normalne odbicie
                                    kula.wektorRuchu.x > 0 && Math.abs(r.x - obiekt.posX) > 100 // prawa strona i w prawo - normalne odbicie
                                    ) {
                                kula.wektorRuchu.y = -kula.wektorRuchu.y;
                                return;
                            }
                            kula.wektorRuchu.x = -kula.wektorRuchu.x;
                            return;
                        }
                        // zmieniamy wektor ruchu w pionie
                        double kat = Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y);
                        kula.wektorRuchu.y = -kula.wektorRuchu.y;
                        //sprawdzamy, czy nie zmieniamy kąta odbcia
                        // uderzenie w platekę 0
                        kat = Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y);
                        double rr = Math.sqrt(Math.pow(kula.wektorRuchu.x, 2) + Math.pow(kula.wektorRuchu.y, 2));
                        if (kula.wektorRuchu.x > 0) {
                            if (kula.posX - obiekt.posX < 0) { //ok
                                kula.wektorRuchu.x = -kula.wektorRuchu.x;
                                kat = Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y);
                            } else if (r.x - obiekt.posX < 10) { //ok
                                kula.wektorRuchu.x = -kula.wektorRuchu.x;
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat -= 10;
                                if (kat < -170) {
                                    kat = -170;
                                } else if (kat > -100) {
                                    kat = -110;
                                }
                                kat = Math.toRadians(kat); //
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX < 20) { //ok
                                kula.wektorRuchu.x = -kula.wektorRuchu.x;
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat -= 20;
                                if (kat < -170) {
                                    kat = -170;
                                } else if (kat > -100) {
                                    kat = -100;
                                }
                                kat = Math.toRadians(kat); //
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX > 124) { //ok
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat -= 30;
                                if (kat > 170) {
                                    kat = 170;
                                } else if (kat < 100) {
                                    kat = 100;
                                }
                                kat = Math.toRadians(kat); //
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX > 114) { //ok
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat -= 20;
                                if (kat > 170) {
                                    kat = 170;
                                } else if (kat < 100) {
                                    kat = 100;
                                }
                                kat = Math.toRadians(kat); //
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX > 104) { //ok
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat -= 10;
                                if (kat > 170) {
                                    kat = 170;
                                } else if (kat < 100) {
                                    kat = 100;
                                }
                                kat = Math.toRadians(kat); //
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            }
                        } else if (kula.wektorRuchu.x < 0) {
                            if (kula.posX - obiekt.posX < 0) {
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat += 30;
                                if (kat > -100) {
                                    kat = -100;
                                } else if (kat < -170) {
                                    kat = -170;
                                }
                                kat = Math.toRadians(kat);
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX < 10) {
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat += 20;
                                if (kat > -100) {
                                    kat = -100;
                                } else if (kat < -170) {
                                    kat = -170;
                                }
                                kat = Math.toRadians(kat);
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX < 20) {
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat += 10;
                                if (kat > -100) {
                                    kat = -100;
                                } else if (kat < -170) {
                                    kat = -170;
                                }
                                kat = Math.toRadians(kat);
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX > 124) {
                                kula.wektorRuchu.x = -kula.wektorRuchu.x;
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                kat = Math.toRadians(kat);
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX > 114) {
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                //kat = Math.toRadians(kat);
                                kat = Math.toRadians(Math.abs(kat) + 20);
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                            } else if (r.x - obiekt.posX > 104) {
                                kat = Math.toDegrees(Math.atan2(kula.wektorRuchu.x, kula.wektorRuchu.y));
                                //kat = Math.toRadians(kat);
                                kat = Math.toRadians(Math.abs(kat) + 10); // - 10 stopni
                                kula.wektorRuchu.y = rr * Math.cos(kat);
                                kula.wektorRuchu.x = rr * Math.sin(kat);
                                //Gra.zapauzujGre();
                            }
                        }
                        x2 = (int) (kula.wektorRuchu.x * kula.getPredkosc() * 10);
                        y2 = (int) (kula.wektorRuchu.y * kula.getPredkosc() * 10);
                        //Gra.zapauzujGre();
                        return;
                    } else {
                        odbitaOP = false; // brak odbicia przez paletke
                    }
                } else {
                    if (kula.sprawdzKolizje(obiekt)) {
                        kL.add((Cegla) obiekt);
                    }
                }
            }
        }
        // sprawdzamy przypadki
        if (kL.size() > 2) { // uderzenie w 3 cegły - zawsze zmieniamy x i y (z drobnym wyjątkiem)
            kula.wektorRuchu.y = -kula.wektorRuchu.y;
            kula.wektorRuchu.x = -kula.wektorRuchu.x;
            // sprawdzamy które cegły tak naprawdę zostały uderzone (posiadają różne x i y)
            if (kL.get(0).matryca.x != kL.get(1).matryca.x && kL.get(0).matryca.y != kL.get(1).matryca.y) { // 0 i 1
                kL.get(0).uderzenie(false);
                kL.get(1).uderzenie(false);
            } else if (kL.get(0).matryca.x != kL.get(2).matryca.x && kL.get(0).matryca.y != kL.get(2).matryca.y) { // 0 i 2
                kL.get(0).uderzenie(false);
                kL.get(2).uderzenie(false);
            } else if (kL.get(2).matryca.x != kL.get(1).matryca.x && kL.get(1).matryca.y != kL.get(2).matryca.y) { // 1 i 2
                kL.get(1).uderzenie(false);
                kL.get(2).uderzenie(false);
            } else { //drobny wyjątek to 3 cegłowy mór poziomy - uderzenie w 3 cegły, bop kula jest większa niż wysokość cegły - odbijamy w poziomie
                kula.wektorRuchu.y = -kula.wektorRuchu.y; //przywracamy prawidłowy wektory Y
                kL.get(1).uderzenie(false); // uderzenie w środkową
            }
        } else if (kL.size() == 2) { // uderzenie w ścianę
            if (kL.get(0).matryca.x != kL.get(1).matryca.x) {// ściana pozioma - odbicie w pionie
                kula.wektorRuchu.y = -kula.wektorRuchu.y;
            } else { // ściana pionowa - odbicie w poziomie
                kula.wektorRuchu.x = -kula.wektorRuchu.x;
            }
            // sprawdzamy która cegła tak naprawdę została uderzona
            Rectangle r1 = kula.getzajmowanaPowierzchnia().intersection(kL.get(0).getzajmowanaPowierzchnia());
            Rectangle r2 = kula.getzajmowanaPowierzchnia().intersection(kL.get(1).getzajmowanaPowierzchnia());
            // porównujemy pola - większe = uderzenie
            if (r1.x * r1.y > r2.x * r2.y) {
                kL.get(0).uderzenie(false);
            } else {
                kL.get(1).uderzenie(false);
            }
        } else if (!kL.isEmpty()) { // uderzenie w pojedynczą cegłę 
            Rectangle rec = kula.getzajmowanaPowierzchnia().intersection(kL.get(0).getzajmowanaPowierzchnia());
            if (!specjalneOdbicie(kula, kL.get(0), rec)) {

                if (rec.width > rec.height) { // odbijamy w pionie
                    kula.wektorRuchu.y = -kula.wektorRuchu.y;
                } else if (rec.width < rec.height) { // odbijamy w poziomie
                    kula.wektorRuchu.x = -kula.wektorRuchu.x;
                } else { // idealny kąt - pion+poziom
                    if (!specjalneOdbicie(kula, kL.get(0), rec)) {
                        kula.wektorRuchu.y = -kula.wektorRuchu.y;
                        kula.wektorRuchu.x = -kula.wektorRuchu.x;
                    }
                }
            }
            kL.get(0).uderzenie(false);
        }
    }

    private boolean specjalneOdbicie(Kula kula, Cegla cegla, Rectangle r) {
        // w przypadku, gdy następuje został wyliczony idealny kąt sprawdzam,
        // czy kula nie uderza w dalszą część cegły - wtedy odbijamy tylko
        // w jednej płaszczyźnie
        if (kula.wektorRuchu.x > 0) { // ruch w prawo
            if (r.x > (cegla.posX + cegla.ruch.getSprite().getWidth() / 2)) { //odbicie w pionie
                kula.wektorRuchu.y = -kula.wektorRuchu.y;
                return true;
            }
            if (kula.wektorRuchu.y > 0) { // ruch w dół
                if (r.y > (cegla.posY + cegla.ruch.getSprite().getHeight() / 2)) { //odbicie w pzoiomie
                    kula.wektorRuchu.x = -kula.wektorRuchu.x;
                    return true;
                }
            }
        } else {
            if (r.x < (cegla.posX + cegla.ruch.getSprite().getWidth() / 2)) { //odbicie w pionie
                kula.wektorRuchu.y = -kula.wektorRuchu.y;
                return true;
            }
            if (kula.wektorRuchu.y > 0) { // ruch w dół
                if (r.y > (cegla.posY + cegla.ruch.getSprite().getHeight() / 2)) { //odbicie w pzoiomie
                    kula.wektorRuchu.x = -kula.wektorRuchu.x;
                    return true;
                }
            }
        }
        return false;
    }

    private void sprawdzCzyKoniec() {
        if (!Gra.iswTrakcie() || Gra.isZapauzowana()) return; 
        for (Obiekt obiekt : (ArrayList<Obiekt>) mO.clone()) {
            if (obiekt.wyswietlany && obiekt.powodujeKolizje && (obiekt instanceof Cegla)) {
                if (obiekt.typ < 9 || obiekt.typ > 12) {
                    return;
                }
            }
        }
        Gra.zapauzujGre();
        usunWszystkieCegly();
        Uklad.NastepnyPoziom();
    }
    
    public void usunWszystkieCegly() {
        for (Obiekt obiekt : (ArrayList<Obiekt>) mO.clone()) {
            if (obiekt instanceof Cegla) {
                usunObiekt(obiekt);
            }
        }
    }

    public void paint() {
        sprawdzCzas();
        Graphics offG = offscreenImage.getGraphics();
        Rozne.bgImage.drawBackgroundImage(offG);
        for (int zBuff = 0; zBuff < 3; zBuff++) {
            if (zBuff >= 0 && zBuff <= 2) {
                for (Obiekt obiekt : (ArrayList<Obiekt>) mO.clone()) {
                    if (obiekt.zBuff == zBuff && obiekt.wyswietlany) {
                        if (obiekt instanceof Kula && Gra.iswTrakcie()) {
                            ((Kula) obiekt).zmienPozycje();
                            sprawdzKolizje((Kula) obiekt);
                        }
                        if (obiekt instanceof Efekt) {
                            switch (obiekt.typ) {
                                case 2:
                                    ((Efekt) obiekt).specjalneAkcje(0);
                                    offG.drawImage(Rozne.zmienRozmiar(obiekt.ruch.getSprite(), obiekt.skalaX, obiekt.skalaY), obiekt.posX, obiekt.posY, null);
                                    ((Efekt) obiekt).specjalneAkcje(1);
                                    break;
                                case 3:
                                    ((Efekt) obiekt).specjalneAkcje(2);
                                    offG.drawImage(Rozne.zmienRozmiar(obiekt.ruch.getSprite(), obiekt.skalaX, obiekt.skalaY), obiekt.posX, obiekt.posY, null);
                                    ((Efekt) obiekt).specjalneAkcje(3);
                                    break;
                                case 5:
                                    ((Efekt) obiekt).specjalneAkcje(4);
                                    break;
                                case 6:
                                    ((Efekt) obiekt).specjalneAkcje(5);
                                    break;
                                default:
                                    break;
                            }
                        }
                        offG.drawImage(Rozne.zmienRozmiar(obiekt.ruch.getSprite(), obiekt.skalaX, obiekt.skalaY), obiekt.posX, obiekt.posY, null);
                    }
                }
            }
        }
        Rozne.kontener.getGraphics().drawImage(offscreenImage, 0, 0, null);
        sprawdzCzyKoniec();
        offG.dispose(); // usuwamy obraz i zwalniamy zasoby        
    }

    public void update() {
        //if (!Gra.iswTrakcie()) return;
        for (Obiekt obiekt : (ArrayList<Obiekt>) mO.clone()) {
            obiekt.ruch.update();
        }
    }

}
