/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import Ramki.RamkaGlowna;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Darek Xperia
 */
public class ObrazTla {

    private Image tlo;

    public ObrazTla(String nazwaPliku) {
        try {
            tlo = (Image)ImageIO.read(getClass().getClassLoader().getResource("Res/"+nazwaPliku)); // próbuj z resource
            if (tlo == null) tlo = Toolkit.getDefaultToolkit().getImage(nazwaPliku);
            MediaManager.mM.addImage(tlo, 0);
            MediaManager.mM.waitForID(0);
        } catch (NullPointerException | InterruptedException | IOException e) {
            System.out.println("ObrazTla:"+e);
        }
        //Rozne.kontener.setSize(tlo.getWidth(Rozne.kontener), tlo.getHeight(Rozne.kontener));
    }

    public Image getImage() {
        return tlo;
    }

    public void setImage(Image image) {
        this.tlo = image;
    }

    public void drawBackgroundImage(Graphics g) {
        //g.drawImage(tlo, 0, 0, Rozne.kontener);
        float prc = ((RamkaGlowna.platforma.posX + (RamkaGlowna.platforma.platformaSpr[0].getWidth()/2)) *100)/1024;
        float start = (prc * (tlo.getWidth(Rozne.kontener)-1024))/100;
        g.drawImage(tlo,0, 0, 1024, 768, (int)start, 0, 1024+(int)start, 768, Rozne.kontener );
    }
    
    
}
