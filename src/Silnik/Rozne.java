/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Darek Xperia
 */
public class Rozne {

    private static BufferedImage spriteSheet;
    private static String nazwaPliku = "";
    public static Component kontener;
    public static ObrazTla bgImage;

    public static final long magicNumber = 71830;

    public static <BufferedImage> BufferedImage[] concatAll(BufferedImage[] first, BufferedImage[]... rest) {
        int totalLength = first.length;
        for (BufferedImage[] array : rest) {
            totalLength += array.length;
        }
        BufferedImage[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (BufferedImage[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File(file));
            nazwaPliku = file;
            MediaManager.mM.addImage(sprite, 0);
            MediaManager.mM.waitForID(0);
        } catch (IOException | InterruptedException e) {
            System.out.println("LoadSprite:" + e);
        }

        return sprite;
    }
    
    private static BufferedImage loadSpriteFromResorce(String filePath) {

        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(Rozne.class.getClassLoader().getResource("Res/"+filePath));
            nazwaPliku = filePath;
            MediaManager.mM.addImage(sprite, 0);
            MediaManager.mM.waitForID(0);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.out.println("LoadSprite:" + e + " " + filePath);
        }

        return sprite;
    }

    public static BufferedImage[] loadSpriteSheet(String fileName, int xStart, int yStart, int sCount, int X_SIZE, int Y_SIZE, boolean doPrzodu) {

        List<BufferedImage> imageList = new ArrayList<>();
        if (!nazwaPliku.equals(fileName) || spriteSheet == null) { // spróbuj z res
            spriteSheet = loadSpriteFromResorce(fileName);
        }
        
        if (!nazwaPliku.equals(fileName) || spriteSheet == null) {
            System.out.println(fileName);
            spriteSheet = loadSprite(fileName);
        }

        for (int n = 0; n < sCount; n++) {
            imageList.add(spriteSheet.getSubimage((xStart + (n * (doPrzodu ? X_SIZE : -X_SIZE))), yStart, X_SIZE, Y_SIZE));
        }
        BufferedImage[] out = new BufferedImage[imageList.size()];
        imageList.toArray(out);
        return out;
    }

    public static BufferedImage[] loadSpriteSheet(String fileName, int xStart, int yStart, int sCount, int X_SIZE, int Y_SIZE, int stepY) {

        List<BufferedImage> imageList = new ArrayList<>();
        if (!nazwaPliku.equals(fileName) || spriteSheet == null) { // spróbuj z res
            spriteSheet = loadSpriteFromResorce(fileName);
        }
        
        if (!nazwaPliku.equals(fileName) || spriteSheet == null) {
            System.out.println(fileName);
            spriteSheet = loadSprite(fileName);
        }
        for (int n = 0; n < sCount; n++) {
            imageList.add(spriteSheet.getSubimage(xStart, yStart + n * stepY, X_SIZE, Y_SIZE));
        }
        BufferedImage[] out = new BufferedImage[imageList.size()];
        imageList.toArray(out);
        return out;
    }

    public static BufferedImage zmienRozmiar(BufferedImage img, float skalaX, float skalaY) {
        if (skalaX == 1.0f && skalaY == 1.0f) {
            return img; // zamiana rozmiaru zbędna
        }
        Image tmp = img.getScaledInstance((int) (img.getWidth() * skalaX), (int) (img.getHeight() * skalaY), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage((int) (img.getWidth() * skalaX), (int) (img.getHeight() * skalaY), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

    public static void zapiszNajlepszy(String filename) {
        File file;
        try {
            if (!filename.equals("")) {
                file = new File(filename);
                PrintWriter zapis = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
                zapis.println(Wynik.getNajlepszy());
                zapis.println(Wynik.getNajlepszy()^magicNumber);
                zapis.close();
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu.");
        }
    }

    public static void wczytajNajlepszy(String filename) {
        File file;
        long lT = 0;
        long lC = 0;
        try {
            if (!filename.equals("")) {
                file = new File(filename);
                Scanner o = new Scanner(file);
                if (o != null) {
                    lT = Long.parseLong(o.nextLine());
                    lC = Long.parseLong(o.nextLine());
                    if ((lT ^ magicNumber) == lC) {
                        Wynik.najlepszyUstaw(lT);
                    }
                    o.close();
                } else {
                    Wynik.najlepszyUstaw(0);
                }
            }
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Błąd odczytu.\n" + e);
        }
    }

}
