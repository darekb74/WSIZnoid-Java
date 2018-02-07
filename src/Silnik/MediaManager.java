/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Component;
import java.awt.MediaTracker;

/**
 *
 * @author Darek Xperia
 */
public class MediaManager {
    protected static Component kontener;
    public static MediaTracker mM;
    public MediaManager (Component kontener) {
        this.kontener = kontener;
        this.mM = new MediaTracker(kontener);
    }
}
