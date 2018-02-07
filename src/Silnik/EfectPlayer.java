/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author Darek Xperia
 */
public class EfectPlayer implements Runnable {

    private Thread watek;
    private final int opoznienie = 500;
    public MediaPlayer[] efekty;
    private long sTimers[];
    private URL[] resource = new URL[10];
    private boolean muted = false;
    private double volume = 0.4;
    
    public EfectPlayer(int iloscDzwiekow) {
        efekty = new MediaPlayer[iloscDzwiekow];
        sTimers = new long[iloscDzwiekow];
        for (int i = 0; i < iloscDzwiekow; sTimers[i++]=0);
        threadStart();
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    private void threadStart() {
        //Utwórz i uruchom nowy wątek
        ladujZasoby();
        watek = new Thread(this);
        watek.start();
    }

    private void ladujZasoby() {
        resource[0] = getClass().getResource("/Res/explosion0.mp3");
        resource[1] = getClass().getResource("/Res/glass0.wav");
        resource[2] = getClass().getResource("/Res/glass1.wav");
        resource[3] = getClass().getResource("/Res/1.mp3");
        resource[4] = getClass().getResource("/Res/2.mp3");
        resource[5] = getClass().getResource("/Res/3.mp3");
        resource[6] = getClass().getResource("/Res/4.mp3");
    }

    public void odtworzEfekt(int efekt) {
        try {

            Media hit = new Media(resource[efekt].toString());
            //Szukamy pierwszego wolnego playera
            for (int i = 0; i < efekty.length; i++) {
                if (efekty[i] == null || // suzkaj pierwszego pustego
                    //efekty[i].getStatus() == Status.STOPPED ||
                    efekty[i].getStatus() == Status.DISPOSED ) { // lub zatrzymanego
                    
                    efekty[i] = new MediaPlayer(hit);
                    efekty[i].setCycleCount(1);
                    efekty[i].setMute(muted);
                    efekty[i].setVolume(volume);
                    efekty[i].setStartTime(hit.getDuration().seconds(0));
                    efekty[i].setStopTime(hit.getDuration());
                    efekty[i].play();
                    sTimers[i]=System.currentTimeMillis(); // start
                    return;
                }
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("AudioPlayer.run:" + e);
        }
    }

    public void run() {

        long czas = System.currentTimeMillis();

        while (true) {//niekończąca się pętla
            try {
                czas += opoznienie;
                // przesukaj magazyn - usun zakonczone
                for (int i = 0; i < efekty.length; i++) {
                    if (efekty[i] != null) {
                        if (efekty[i].getCurrentTime().toMillis() >= efekty[i].getMedia().getDuration().toMillis() || czas > sTimers[i] + 3000) {
                            efekty[i].stop(); // zatrzymaj
                            sTimers[i] = 0; // wyzeruj timer
                        }
                        if (efekty[i].getStatus() == Status.STOPPED) {
                            efekty[i].dispose(); // zwolnij pamiec jesli zatrzymany
                        }
                    }
                }
                watek.sleep(Math.max(0, czas - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                System.out.println("AudioPlayer.run:" + e);
            }
        }
    }
}
