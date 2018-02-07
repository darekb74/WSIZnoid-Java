/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Darek Xperia
 */
public class AudioPlayer implements Runnable{

    private Thread watek;
    private final int opoznienie = 1000;
    public MediaPlayer mediaPlayer;
    private String res;

    public AudioPlayer(String res) {
        this.res = res;
        threadStart();
    }

    private void threadStart() {
        //Utwórz i uruchom nowy wątek
        watek = new Thread(this);
        watek.start();
    }

    public void run() {

        long czas = System.currentTimeMillis();
        try {
                final URL resource = getClass().getResource("/Res/"+res);
                Media hit = new Media(resource.toString());
                mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.setVolume(0.2);
                //mediaPlayer.se
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setStartTime(hit.getDuration().seconds(0));
                mediaPlayer.setStopTime(hit.getDuration());
                
                mediaPlayer.play();
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println("AudioPlayer.run:" + e);
            }
        while (true) {//niekończąca się pętla
            try {
                czas += opoznienie;
                watek.sleep(Math.max(0, czas - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                System.out.println("AudioPlayer.run:" + e);
            }
        }
    }
}
