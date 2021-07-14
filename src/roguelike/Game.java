
package roguelike;

import View.Camera;
import MapGeneration.CaveGenerator;
import java.awt.Color;
import roguelike.Objects.Player.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import roguelike.Items.ItemDagger;
import roguelike.Items.ParentItem;

/**
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
        ".#.......d......C..#.......#." + 
        ".#.......d......C..#.......#." + 
        ".#.......d......#..#.......#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
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

        this.gameboard = new Board(400);
        
        new CaveGenerator(gameboard, 300, 2);
        
        this.gameboard = new Board(29, seed);
        
        this.player = gameboard.GetPlayer();

        camera = gameboard.camera;

        UpdateView();

    }
    
    //Updates the board to reflect changes
    private void Update() {

        gameboard.Update();
        UpdateView();

    }
    
    private void UpdateView() {
        
        //Initialize formating variables
        
        String s;
        StyledDocument doc;
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setBackground(set, Color.BLACK);
        StyleConstants.setForeground(set, Color.WHITE);
        
        //Update camera screen
        
        camera.GetView(jGameScreen);
        
        //Update message board
        
        ArrayList<String> messages = camera.GetMessages();
        
        s = "";
        
        for (String m : messages) {
            
            s += m + "\n";
            
        }
        
        jMessageBoard.setText(s);
        
        //Format message board

        doc = jMessageBoard.getStyledDocument();
        
        for (int k = 0; k < s.length(); k++) {
            
            doc.setCharacterAttributes(k, 1, set, true);
            
        }
        
        //Update stats board
        
        s = " ";
        
        s += player.name + " (" + player.classLevel + ")";
        while (s.length() < 28) {s += " ";}
        
        s += "\n Hitpoints: " + player.hitPoints + " / " + player.maxHitPoints;
        while (s.length() < 28 * 2 + 1) {s += " ";}
        
        s += "\n STR: " + player.statStrength;
        while (s.length() < 28 * 2 + 1 + 15) {s += " ";}
        s += "DEX: " + player.statDexterity;
        while (s.length() < 28 * 3 + 2) {s += " ";}
        
        s += "\n CON: " + player.statConstitution;
        while (s.length() < 28 * 3 + 2 + 15) {s += " ";}
        s += "WIS: " + player.statWisdom;
        while (s.length() < 28 * 4 + 3) {s += " ";}
        
        s += "\n INT: " + player.statIntelligence;
        while (s.length() < 28 * 4 + 3 + 15) {s += " ";}
        s += "CHA: " + player.statCharisma;
        while (s.length() < 28 * 5 + 4) {s += " ";}
        
        
        for (int i = 0; i < player.inventory.inventory.length; i++) {
            
            ParentItem item = player.inventory.inventory[i];
            
            s += "\n • ";
            
            if (item != null) {
                
                s += item.itemName;
                
            }
            
            while (s.length() < 28 * (6 + i) + (5 + i)) {s += " ";}
        }
        
        jStatsBoard.setText(s);
        
        //Format message board

        doc = jStatsBoard.getStyledDocument();
        
        for (int k = 0; k < s.length(); k++) {
            
            doc.setCharacterAttributes(k, 1, set, true);
            
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jStatsBoard = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jMessageBoard = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setAlignmentX(0.0F);
        jScrollPane2.setAlignmentY(0.0F);
        jScrollPane2.setFocusable(false);
        jScrollPane2.setHorizontalScrollBar(null);

        jGameScreen.setBackground(new java.awt.Color(0, 0, 0));
        jGameScreen.setBorder(null);
        jGameScreen.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jGameScreen.setFocusable(false);
        jGameScreen.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jGameScreen.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(jGameScreen);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setAlignmentX(0.0F);
        jScrollPane1.setAlignmentY(0.0F);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setHorizontalScrollBar(null);

        jStatsBoard.setBackground(new java.awt.Color(0, 0, 0));
        jStatsBoard.setBorder(null);
        jStatsBoard.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jStatsBoard.setFocusable(false);
        jStatsBoard.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jScrollPane1.setViewportView(jStatsBoard);

        jScrollPane3.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setAlignmentX(0.0F);
        jScrollPane3.setAlignmentY(0.0F);
        jScrollPane3.setFocusable(false);
        jScrollPane3.setHorizontalScrollBar(null);

        jMessageBoard.setBackground(new java.awt.Color(0, 0, 0));
        jMessageBoard.setBorder(null);
        jMessageBoard.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jMessageBoard.setFocusable(false);
        jMessageBoard.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jScrollPane3.setViewportView(jMessageBoard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
    private javax.swing.JTextPane jMessageBoard;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jStatsBoard;
    // End of variables declaration//GEN-END:variables
}
