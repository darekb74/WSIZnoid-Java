/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Darek Xperia
 */
public class MenadzerTekstur {

    private static HashMap<String, BufferedImage[]> mT = new HashMap<String, BufferedImage[]>();

    public static void wczytajTekstury() {
        // pusty
        mT.put("emptySpr", Rozne.loadSpriteSheet("plasmaball.png", 0, 0, 1, 1, 1, true));
        // postac - ruch (do usuniecia)
        //mT.put("postac_lewo", Rozne.loadSpriteSheet("AnimationSpriteSheet.png", 0, 32, 3, 32, 32, true));
        //mT.put("postac_prawo", Rozne.loadSpriteSheet("AnimationSpriteSheet.png", 0, 64, 3, 32, 32, true));
        //mT.put("postac_gora", Rozne.loadSpriteSheet("AnimationSpriteSheet.png", 0, 96, 3, 32, 32, true));
        //mT.put("postac_dol", Rozne.loadSpriteSheet("AnimationSpriteSheet.png", 0, 0, 3, 32, 32, true));
        //mT.put("postac_stop", Rozne.loadSpriteSheet("AnimationSpriteSheet.png", 32, 0, 1, 32, 32, true));
        // Efekty
        mT.put("plasma", Rozne.concatAll(
                Rozne.loadSpriteSheet("plasmaball.png", 0, 0, 4, 128, 128, true),
                Rozne.loadSpriteSheet("plasmaball.png", 0, 128, 4, 128, 128, true),
                Rozne.loadSpriteSheet("plasmaball.png", 0, 256, 4, 128, 128, true),
                Rozne.loadSpriteSheet("plasmaball.png", 0, 384, 4, 128, 128, true)
                ));
        mT.put("silnik_maly_niebieski", Rozne.concatAll(
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 224, 8, 32, 32, false),
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 192, 8, 32, 32, false),
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 160, 8, 32, 32, false),
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 128, 8, 32, 32, false),
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 96, 8, 32, 32, false),
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 64, 8, 32, 32, false),
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 32, 8, 32, 32, false),
                Rozne.loadSpriteSheet("fire_thruster_sb.png", 224, 0, 8, 32, 32, false)
                ));
        mT.put("silnik_duzy", Rozne.concatAll(
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 448, 8, 64, 64, false),
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 384, 8, 64, 64, false),
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 320, 8, 64, 64, false),
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 256, 8, 64, 64, false),
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 192, 8, 64, 64, false),
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 128, 8, 64, 64, false),
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 64, 8, 64, 64, false),
                Rozne.loadSpriteSheet("fire_thruster.png", 448, 0, 8, 64, 64, false)
                ));
        //mT.put("planeta", Rozne.concatAll(
                //Rozne.loadSpriteSheet("planet.png", 0, 0, 5, 64, 64, true),
                //Rozne.loadSpriteSheet("planet.png", 0, 64, 5, 64, 64, true),
                //Rozne.loadSpriteSheet("planet.png", 0, 128, 5, 64, 64, true),
                //Rozne.loadSpriteSheet("planet.png", 0, 192, 4, 64, 64, true)
               // ));
        mT.put("dym", Rozne.concatAll(
                Rozne.loadSpriteSheet("smoke_s.png", 0, 0, 8, 64, 64, true),
                Rozne.loadSpriteSheet("smoke_s.png", 0, 64, 8, 64, 64, true),
                Rozne.loadSpriteSheet("smoke_s.png", 0, 128, 8, 64, 64, true),
                Rozne.loadSpriteSheet("smoke_s.png", 0, 192, 8, 64, 64, true),
                Rozne.loadSpriteSheet("smoke_s.png", 0, 256, 8, 64, 64, true),
                Rozne.loadSpriteSheet("smoke_s.png", 0, 0, 8, 1, 1, true)
                ));
        mT.put("eksplozja", Rozne.concatAll(
                Rozne.loadSpriteSheet("explosion.png", 0, 0, 8, 1, 1, true),
                Rozne.loadSpriteSheet("explosion.png", 0, 0, 8, 128, 128, true),
                Rozne.loadSpriteSheet("explosion.png", 0, 128, 8, 128, 128, true),
                Rozne.loadSpriteSheet("explosion.png", 0, 256, 8, 128, 128, true),
                Rozne.loadSpriteSheet("explosion.png", 0, 383, 8, 128, 128, true),
                Rozne.loadSpriteSheet("explosion.png", 0, 512, 8, 128, 128, true)
                ));

        // cegły
        mT.put("biala", Rozne.loadSpriteSheet("cegly.png", 0, 0, 1, 42, 20, true));
        mT.put("pomaranczowa", Rozne.loadSpriteSheet("cegly.png", 42, 0, 1, 42, 20, true));
        mT.put("niebieska", Rozne.loadSpriteSheet("cegly.png", 84, 0, 1, 42, 20, true));
        mT.put("zielona", Rozne.loadSpriteSheet("cegly.png", 126, 0, 1, 42, 20, true));
        mT.put("czerwona", Rozne.loadSpriteSheet("cegly.png", 168, 0, 1, 42, 20, true));
        mT.put("granatowa", Rozne.loadSpriteSheet("cegly.png", 210, 0, 1, 42, 20, true));
        mT.put("rozowa", Rozne.loadSpriteSheet("cegly.png", 252, 0, 1, 42, 20, true));
        mT.put("zolta", Rozne.loadSpriteSheet("cegly.png", 294, 0, 1, 42, 20, true));
        mT.put("wybuchajaca_a", Rozne.concatAll(
                Rozne.loadSpriteSheet("cegly.png", 0, 100, 5, 42, 20, true),
                Rozne.loadSpriteSheet("cegly.png", 168, 100, 5, 42, 20, false)
                ));
        mT.put("szara_a", Rozne.loadSpriteSheet("cegly.png", 0, 20, 10, 42, 20, true));
        mT.put("zielona_a", Rozne.loadSpriteSheet("cegly.png", 0, 40, 10, 42, 20, true));
        mT.put("czerwona_a", Rozne.loadSpriteSheet("cegly.png", 0, 60, 10, 42, 20, true));
        mT.put("niebieska_a", Rozne.loadSpriteSheet("cegly.png", 0, 80, 10, 42, 20, true));
        mT.put("szklana", Rozne.loadSpriteSheet("cegly.png", 0, 120, 10, 42, 20, true));
        // w sumie poniższe to efekty, ale są w pliku z cegłami :P
        mT.put("pekniecie1", Rozne.loadSpriteSheet("cegly.png", 210, 100, 1, 42, 20, true));
        mT.put("pekniecie2", Rozne.loadSpriteSheet("cegly.png", 252, 100, 1, 42, 20, true));
        mT.put("pekniecie3", Rozne.loadSpriteSheet("cegly.png", 294, 100, 1, 42, 20, true));
        mT.put("pekniecie4", Rozne.loadSpriteSheet("cegly.png", 336, 100, 1, 42, 20, true));
        mT.put("pekniecie5", Rozne.loadSpriteSheet("cegly.png", 378, 100, 1, 42, 20, true));
        
        // platforma
        mT.put("platforma", Rozne.loadSpriteSheet("platforma_ss.png", 0, 0, 10, 144, 24, true));
        // kula
        //mT.put("kula", Rozne.loadSpriteSheet("ball_spr.png", 0, 0, 20, 44, 44, true));
        
        //mT.put("kula_full", Rozne.concatAll(
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 0, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 24, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 48, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 72, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 96, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 120, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 144, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 168, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 192, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 216, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 240, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 264, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 288, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 312, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 336, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 360, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 384, 36, 24, 24, true),
                //Rozne.loadSpriteSheet("ball_full_rotation_c.png", 0, 408, 36, 24, 24, true)
                //));
        mT.put("kula_full1", Rozne.concatAll(
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 0, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 24, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 48, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 72, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 96, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 120, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 144, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 168, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 192, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 216, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 240, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 264, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 288, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 312, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 336, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 360, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 384, 36, 24, 24, true),
                Rozne.loadSpriteSheet("ball_full_rotation_red.png", 0, 408, 36, 24, 24, true)
                ));
        mT.put("kula_full2", Rozne.concatAll(
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 0, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 16, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 32, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 48, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 64, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 80, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 96, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 112, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 128, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 144, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 160, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 176, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 192, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 208, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 224, 34, 16, 16, true),
                Rozne.loadSpriteSheet("ball_full_rotation_by.png", 0, 240, 34, 16, 16, true)
                ));
        //mT.put("liczby", Rozne.loadSpriteSheet("numbers_spr.png", 0, 0, 99, 45, 50, 5));
        mT.put("liczby2", Rozne.loadSpriteSheet("numbers_spr_s.png", 0, 0, 49, 23, 25, 5));
        
        mT.put("wynik", Rozne.loadSpriteSheet("napisy_gui.png", 5, 0, 1, 160, 28, true));
        mT.put("pilki", Rozne.loadSpriteSheet("napisy_gui.png", 5, 28, 1, 132, 28, true));
        mT.put("najlepszy", Rozne.loadSpriteSheet("napisy_gui.png", 5, 56, 1, 250, 28, true));
        
        // napisy 2
        mT.put("W", Rozne.loadSpriteSheet("napisy3_gui.png", 0, 0, 1, 72, 103, true));
        mT.put("S", Rozne.loadSpriteSheet("napisy3_gui.png", 72, 0, 1, 78, 103, true));
        mT.put("I", Rozne.loadSpriteSheet("napisy3_gui.png", 150, 0, 1, 25, 103, true));
        mT.put("Z", Rozne.loadSpriteSheet("napisy3_gui.png", 175, 0, 1, 71, 103, true));
        mT.put("N", Rozne.loadSpriteSheet("napisy3_gui.png", 246, 0, 1, 77, 103, true));
        mT.put("O", Rozne.loadSpriteSheet("napisy3_gui.png", 323, 0, 1, 110, 103, true));
        mT.put("D", Rozne.loadSpriteSheet("napisy3_gui.png", 458, 0, 1, 70, 103, true));
        
        mT.put("pauza", Rozne.loadSpriteSheet("napisy3_gui.png", 0, 114, 1, 85,29, true));
        mT.put("spacja", Rozne.loadSpriteSheet("napisy3_gui.png", 98, 114, 1, 441, 29, true));
        mT.put("enter", Rozne.loadSpriteSheet("napisy3_gui.png", 0, 143, 1, 490, 29, true));
        mT.put("koniec", Rozne.loadSpriteSheet("napisy3_gui.png", 0, 168, 1, 181, 29, true));
    }

    public static BufferedImage[] getTekstura(String identyfikator) {
        // pusty
        if (mT.containsKey(identyfikator)) {
            return mT.get(identyfikator);
        } else {
            throw new IllegalArgumentException("Nie ma tekstury o podanym identyfikatorze: " + identyfikator);
        }

    }

}
