/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ramki;

import Silnik.AudioPlayer;
import Silnik.Platforma;
import Silnik.ClsThread;
import Silnik.EfectPlayer;
import Silnik.Rozne;
import Silnik.MenadzerObiektow;
import Silnik.Kula;
import Silnik.Efekt;
import Silnik.Gra;
import Silnik.ObrazTla;
import Silnik.MediaManager;
import Silnik.Wynik;
import Silnik.MenadzerTekstur;
import Silnik.MenuGlowne;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import static java.awt.event.InputEvent.*;
import javafx.embed.swing.JFXPanel;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Darek Xperia
 */
public class RamkaGlowna extends javax.swing.JFrame {

    private MediaManager mM = new MediaManager(this);
    public static MenadzerObiektow mO;

    protected static boolean wcisniety = false;
    protected static int klawisz = 0;
    // Sprite'y
    //private Postac postac;
    public static Platforma platforma;
    public static Kula kula;
    //private Efekt efekt1;
    public static Efekt dyszaNm;
    public static Efekt dyszaZd;
    // Wątek wywołujący update
    private ClsThread updateThread;
    // muzyka
    private AudioPlayer tlo;
    // efekty
    public static EfectPlayer efekt;

    // tło
    //private ObrazTla bgImage;
    /**
     * Creates new form Testing_Sprite
     */
    public RamkaGlowna() {
        super("WSIZnoid JAVA (Darek B. - 6058)");
        initComponents();
        // aby poprawnie zainicjować MediaPlayer
        // poza tym zbędne
        final JFXPanel fxPanel = new JFXPanel();

        this.jButton2.setFocusable(false);
        this.setSize(1191, 795);

        // ustawiam obszar do wyświetlania grafiki
        Rozne.kontener = jPanel1;
        // dodaje obiekty do menadzera
        MenadzerTekstur.wczytajTekstury(); // najpierw wczytujemy wszystkie tekstury

        platforma = new Platforma(1, 700);
        dyszaNm = new Efekt(100, 100, 2);
        dyszaZd = new Efekt(100, 100, 3);

        mO = new MenadzerObiektow();
        mO.dodajObiekt(platforma);
        platforma.setzBuff(2);
        mO.dodajObiekt(dyszaNm);
        dyszaNm.setzBuff(2);
        dyszaNm.ustawObiektOdniesienia(platforma);

        mO.dodajObiekt(dyszaZd);
        dyszaZd.setzBuff(1);
        dyszaZd.ustawObiektOdniesienia(platforma);
        dyszaZd.wyswietlany(false);

        kula = new Kula(500, 500);
        mO.dodajObiekt(kula);
        kula.setzBuff(2);

        Wynik.reset();
        Wynik.resetN();
        Wynik.najlepszyUstaw(Wynik.getNajlepszy());
        Wynik.resetP();
        MenuGlowne.LogoInit();

        // wczytuje grafikę tła
        Rozne.bgImage = new ObrazTla("bg4_big.jpg");

        // ustawia platforme na środku
        platforma.ustawNaSrodkuX();

        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left_pressed");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right_pressed");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "space_pressed");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "esc_pressed");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enter_pressed");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "left_released");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "right_released");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released SPACE"), "space_released");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released ESCAPE"), "esc_released");
        jPanel1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released ENTER"), "enter_released");
        jPanel1.getActionMap().put("left_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b1) != 0 || Gra.isZapauzowana()) {
                    return;
                }
                //System.out.println("WCIESNIETO LEWO");
                klawisz |= 0b1;
            }
        });
        jPanel1.getActionMap().put("right_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b10) != 0 || Gra.isZapauzowana()) {
                    return;
                }
                //System.out.println("WCIESNIETO PRAWO");
                klawisz |= 0b10;
            }
        });
        jPanel1.getActionMap().put("space_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b100) != 0) {
                    return;
                }
                //System.out.println("WCIESNIETO SPACJE");
                dyszaNm.zmienWielkosc(1.0f, 1.5f);
                dyszaZd.wyswietlany(true);
                klawisz |= 0b100;
                if (!Gra.iswTrakcie()) {
                    return;
                }
                // pauza
                if (Gra.isZapauzowana()) {
                    Gra.odpauzujGre();
                } else {
                    Gra.zapauzujGre();
                }
            }
        });
        jPanel1.getActionMap().put("esc_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b1000) != 0) {
                    return;
                }
                //System.out.println("WCIESNIETO ESC");
                klawisz |= 0b1000;
            }
        });
        jPanel1.getActionMap().put("enter_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b10000) != 0) {
                    return;
                }
                if (!Gra.iswTrakcie()) {
                    Gra.rozpocznijGre();
                }
                //System.out.println("WCIESNIETO ENTER");
                klawisz |= 0b10000;
            }
        });
        jPanel1.getActionMap().put("left_released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b1) == 0) {
                    return;
                }
                //System.out.println("PUSZCZONO LEWO");
                platforma.movX = 0;
                klawisz ^= 0b1;
            }
        });
        jPanel1.getActionMap().put("right_released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b10) == 0) {
                    return;
                }
                //System.out.println("PUSZCZONO PRAWO");
                platforma.movX = 0;
                klawisz ^= 0b10;
            }
        });
        jPanel1.getActionMap().put("space_released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b100) == 0) {
                    return;
                }
                //System.out.println("PUSZCZONO SPACJE");
                dyszaNm.zmienWielkosc(1.0f, 1.0f);
                dyszaZd.wyswietlany(false);
                klawisz ^= 0b100;
            }
        });
        jPanel1.getActionMap().put("esc_released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b1000) == 0) {
                    return;
                }
                //System.out.println("PUSZCZONO ESC");
                klawisz ^= 0b1000;
            }
        });
        jPanel1.getActionMap().put("enter_released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((klawisz & 0b10000) == 0) {
                    return;
                }
                //System.out.println("PUSZCZONO ENTER");
                klawisz ^= 0b10000;
            }
        });

        // wątek wywołujący aktualizacje
        updateThread = new ClsThread(this);
        // muzyka

        tlo = new AudioPlayer("Noisestorm-Barracuda.mp3");
        efekt = new EfectPlayer(20); // max 20 efektów dźwiękowych w tym samym czasie
        
    }

    private void sprawdzKlawiature() {
        if (!Gra.isZapauzowana() && Gra.iswTrakcie()) {
            if ((klawisz & 0b1) > 0) {
                platforma.movX = -5;
            }
            if ((klawisz & 0b10) > 0) {
                platforma.movX = 5;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSlider2 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setName("WSIZnoid JAVA (Darek B. - 6058)"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(1191, 788));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));
        jPanel1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jButton2.setText("PAUZA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSlider1.setToolTipText("");
        jSlider1.setValue(20);
        jSlider1.setFocusable(false);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel1.setText("Głośność muzyki:");

        jCheckBox1.setText("Wycisz muzykę");
        jCheckBox1.setFocusable(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jSlider2.setToolTipText("");
        jSlider2.setFocusable(false);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel2.setText("Głośność efektów:");

        jCheckBox2.setText("Wycisz efekty");
        jCheckBox2.setFocusable(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Praca semestralna nr 2");

        jLabel4.setText("WSIZ Copernicus");

        jLabel5.setText("autor: Dariusz B.");

        jLabel6.setText("nr albumu: 6058");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jCheckBox1)
                        .addComponent(jLabel2)
                        .addComponent(jButton2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jCheckBox2)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(109, 109, 109)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        if ((evt.getModifiersEx() & BUTTON1_DOWN_MASK) > 0) {
            //postac.ruch = postac.animWLewo;
            //postac.ruch.start();
            //postac.movX = -5;
        } else if ((evt.getModifiersEx() & BUTTON3_DOWN_MASK) > 0) {
            //postac.ruch = postac.animWPrawo;
            //postac.ruch.start();
            //postac.movX = 5;
        }

    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        //postac.ruch.stop();
        //postac.movX = 0;
    }//GEN-LAST:event_formMouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Rozne.zapiszNajlepszy("highscore.dat");
    }//GEN-LAST:event_formWindowClosing

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox2.isSelected()) {
            efekt.setMuted(true);
        } else {
            efekt.setMuted(false);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        // TODO add your handling code here:
        if (!jCheckBox2.isSelected()) {
            efekt.setVolume((double) jSlider2.getValue() / (double) 100);
        }
    }//GEN-LAST:event_jSlider2StateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            tlo.mediaPlayer.setMute(true);
        } else {
            tlo.mediaPlayer.setMute(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        if (!jCheckBox1.isSelected()) {
            tlo.mediaPlayer.setVolume((double) jSlider1.getValue() / (double) 100);
        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Gra.isZapauzowana()) {
            Gra.odpauzujGre();
        } else {
            Gra.zapauzujGre();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RamkaGlowna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RamkaGlowna().setVisible(true);

            }
        });
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        sprawdzKlawiature();
        mO.update();
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        MenuGlowne.RuszajLogo();
        Wynik.wyswietlElementy();
        mO.paint();
        jPanel2.repaint(); // wyświetl panel z prawej
        //Toolkit.getDefaultToolkit().sync();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    // End of variables declaration//GEN-END:variables
}
