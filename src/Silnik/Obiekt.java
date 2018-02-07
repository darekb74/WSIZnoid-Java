/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Rectangle;

/**
 *
 * @author Darek Xperia
 */
public class Obiekt {
    public int posX, posY;
    public int movX, movY;
    protected float skalaX;
    protected float skalaY;
    public Animacja ruch;
    protected int zBuff; //0 - tył, 1 - środek, 2 - front
    protected int typ;
    public boolean powodujeKolizje = false;
    
    protected boolean wyswietlany = true;
    
    public Obiekt() {
        posX = 0;
        posY = 0;
        movX = 0;
        movY = 0;
        skalaX = 1.0f;
        skalaY = 1.0f;
        zBuff = 0;
    }
    
    public Obiekt(int x, int y) {
        posX = x;
        posY = y;
        movX = 0;
        movY = 0;
        skalaX = 1.0f;
        skalaY = 1.0f;
        zBuff = 0;
    }
    
    public void zmienKoordynaty() {
        posX += movX;
        posY += movY;
    }
    
    public void zmienWielkosc(float skalaX, float skalaY) {
        this.skalaX = skalaX < 0 ? 0 : skalaX;
        this.skalaY = skalaY < 0 ? 0 : skalaY;
    }
    

    public int getzBuff() {
        return zBuff;
    }

    public void setzBuff(int zBuff) {
        this.zBuff = (zBuff > 2 ? 2 : (zBuff < 0 ? 0 : zBuff));
    }
    
    public void wyswietlany(boolean b) {
        wyswietlany = b;
    }
    
    public Rectangle getzajmowanaPowierzchnia () {
        return new Rectangle(posX, posY, ruch.getSprite().getWidth(), ruch.getSprite().getHeight());
    }   
}
