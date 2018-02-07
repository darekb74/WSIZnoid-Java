/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silnik;

import java.awt.Point;
import Ramki.RamkaGlowna;

/**
 *
 * @author Darek Xperia
 */
public class Uklad {
    // 12345678901234567890

    private static String[] uklad = {
        " 111111111111111111 "
        + "22222222222222222222"
        + "34343434334343434343"
        + " 004000400004000400 "
        + " 7d6d6d7d77d7d6d6d7 "
        + "98989898988989898989"
        + "aaaaaaaaaaaaaaaaaaaa"
        + "aa8898989898989898aa"
        + "bcbbcbbbcbbbcbbcbbcb"
        + "c8c8c8c8c88c8c8c8c8c"
        + "dddddddddddddddddddd"
        + "                    "
        + "                    "
        + "                    "
        + "                    ",
        
          "       00000        "
        + "     000007000      "
        + "    00000007700     "
        + "   0000000770000    "
        + "   0000077770000    "
        + "  000007777700000   "
        + "  000078778700000   "
        + "  000077777700770   "
        + "  0770077d7777000   "
        + "  000777777770000   "
        + "   0000077777000    "
        + "   0000007777700    "
        + "    00000007770     "
        + "     0000000077     "
        + "       00000   7    ",

          "0                   "
        + "00                  "
        + "001           ddd   "
        + "0011         ddddd  "
        + "00112         ddd   "
        + "001122              "
        + "0011223             "
        + "00112233            "
        + "001122334           "
        + "0011223344          "
        + "00112233445         "
        + "001122334455        "
        + "00112233445567777777"
        + "08182838485868787878"
        + "99999999999999999999",
          
          "      0      0      "
        + "      0      0      "
        + "       0    0       "
        + "       0    0       "
        + "     5555555555     "
        + "     5555555555     "
        + "    555b5555b555    "
        + "    555855558555    "
        + "   55555555555555   "
        + "   55555555555555   "
        + "  255555bbbb555552  "
        + "  2555555555555552  "
        + "  2555555555555552  "
        + "  2 5          5 2  "
        + "  2 c          c 2  ",
            
          "aaaaaaaaaaaaaaaaaaaa"
        + "a1234567           a"
        + "a2345671           a"
        + "a3456712           a"
        + "bbbbbbbbbbbbbbbbdddb"
        + "b                  b"
        + "b                  b"
        + "b                  b"
        + "cdddcccccccccccccccc"
        + "c                  c"
        + "c                  c"
        + "c                  c"
        + "9999999999999999ddd9"
        + "                    "
        + "                    ",
        
          "                    "
        + "                    "
        + "                    "
        + "                    "
        + "                    "
        + "                    "
        + "                    "
        + "                    "
        + "                    "
        + "        abc         "
        + "        789         "
        + "        456         "
        + "        123         "
        + "        ddd         "
        + "                    "

    };
    public static Cegla[] ceg = new Cegla[300];

    public static void NastepnyPoziom() {
        if (Gra.obecnyPoziom < uklad.length-1) {
            Gra.obecnyPoziom++;
        } else {
            Gra.obecnyPoziom=0;
        }
        wyswietlUklad(Gra.obecnyPoziom);
        RamkaGlowna.platforma.ustawNaSrodkuX();
        RamkaGlowna.kula.reset();
    }
    
    public static void wyswietlUklad(int u) {
        int x = 0, y = 0;
        for (char ch : uklad[u].toCharArray()) {
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '9':
                    ceg[x + y * 20] = new Cegla(92 + 42 * x, 100 + y * 20, (int) ch - 48);
                    ceg[x + y * 20].powodujeKolizje = true;
                    break;
                case '8':
                    ceg[x + y * 20] = new Cegla(92 + 42 * x, 100 + y * 20, (int) ch - 48);
                    ceg[x + y * 20].powodujeKolizje = true;
                    // dodaj dym
                    ceg[x + y * 20].dodajObiektPowiazany(1);
                    // dodaj wybuch
                    ceg[x + y * 20].dodajObiektPowiazany(2);
                    break;
                case 'a':
                case 'b':
                case 'c':
                    ceg[x + y * 20] = new Cegla(92 + 42 * x, 100 + y * 20, (int) ch - 87);
                    ceg[x + y * 20].powodujeKolizje = true;
                    break;
                case 'd':
                case 'e':
                case 'f':
                    ceg[x + y * 20] = new Cegla(92 + 42 * x, 100 + y * 20, (int) ch - 87);
                    ceg[x + y * 20].powodujeKolizje = true;
                    ceg[x + y * 20].dodajObiektPowiazany(3);
                    break;
                default:
                    ceg[x + y * 20] = new Cegla(92 + 42 * x, 100 + y * 20, -1); // pusta cegla
                    ceg[x + y * 20].powodujeKolizje = false;
                    break;
            }
            ceg[x + y * 20].matryca = new Point(x, y);
            RamkaGlowna.mO.dodajObiekt(ceg[x + y * 20]);
            ceg[x + y * 20].setzBuff(1);
            x++;
            if (x >= 20) {
                x = 0;
                y++;
            }
        }
    }

}
