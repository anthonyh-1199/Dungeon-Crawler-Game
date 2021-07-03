/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import View.Camera;
import MapGeneration.CaveGenerator;
import java.awt.Color;
import roguelike.Objects.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Anthony
 */
public class Game extends javax.swing.JFrame implements KeyListener {

    //Initialize game elements
    Board gameboard;
    Player player;
    Camera camera;

    public Game() {
        initComponents();
        this.addKeyListener(this);
        
        String seed =
        "............................." + 
        ".###########################." + 
        ".#..............C..#.......#." + 
        ".#..............C..#.......#." + 
        ".#..............#..#.......#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#......P.......#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#......#...#." + 
        ".#..............#......#...#." + 
        ".#..............#......#GGG#." + 
        ".###########################." +
        ".............................";
        
        this.gameboard = new Board(29, seed);
        
        //this.gameboard = new Board(200);
        
        //new CaveGenerator(gameboard);
        
        this.player = gameboard.GetPlayer();
        
        camera = new Camera(player, gameboard, 35);

        UpdateView();
    }
    
    //Updates the board to reflect changes
    private void Update() {
        gameboard.Update();
        UpdateView();
    }
    
    private void UpdateView() {
        String viewDataSymbols = camera.GetSymbols();
        Color[] viewDataColors = camera.GetColors();

        jGameScreen.setText(viewDataSymbols);
        StyledDocument doc = jGameScreen.getStyledDocument();
        
        for (int i = 0; i < viewDataSymbols.length(); i++) {
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setBackground(set, new Color(0, 0, 0));
            if (viewDataColors[i] != null){
                StyleConstants.setForeground(set, viewDataColors[i]);
            }
            doc.setCharacterAttributes(i, 1, set, true);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jGameScreen = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jGameScreen.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jGameScreen.setFocusable(false);
        jScrollPane2.setViewportView(jGameScreen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        
        player = gameboard.GetPlayer();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.Move("UP");
                break;
            case KeyEvent.VK_DOWN:
                player.Move("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                player.Move("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                player.Move("RIGHT");
                break;
        }
        
        Update();

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane jGameScreen;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
