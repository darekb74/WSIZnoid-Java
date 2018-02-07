/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Point;

/**
 *
 * @author Darek Xperia
 */
public class Punkt extends Point {
    
    public double x;
    public double y;
    
    public Punkt(double x, double y)  {
        this.x=x;
        this.y=y;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    @Override
    public Punkt getLocation() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void translate(Punkt p) {
        x += p.x;
        y += p.y;
    }
    
    
}
