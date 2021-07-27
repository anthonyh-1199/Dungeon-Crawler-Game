
package roguelike;

import view.Camera;
import java.awt.Color;
import roguelike.objects.entities.player.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.text.*;
import mapgeneration.DungeonGenerator;
import roguelike.items.*;

/**
 * @author Anthony
 */
public class Game extends javax.swing.JFrame implements KeyListener {

    //Initialize game elements
    Board gameboard;
    Player player;
    Camera camera;
    String focus;

    public Game() {
        initComponents();
        this.addKeyListener(this);
        
        String seed =
        "............................." + 
        ".###########################." + 
        ".#.......d.........#.......#." + 
        ".#.......d.........#.......#." + 
        ".#.......d......#..#.......#." + 
        ".#.......d..P...#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#.......d.....C#..#...#...#." + 
        ".#.......d......#..#...#...#." + 
        ".#..............#..#.G.#...#." + 
        ".#..............#..#...#...#." + 
        ".#..............#..#...#...#." + 
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
        ".#..............#......#.G.#." + 
        ".#..............#......#...#." + 
        ".###########################." +
        ".............................";

        this.gameboard = new Board(600);
        
        //DungeonGenerator dg = new DungeonGenerator(gameboard);
        
        this.focus = "player";
        
        //new CaveGenerator(gameboard, 300, 1);
        
        this.gameboard = new Board(29, seed);
        
        this.player = gameboard.getPlayer();

        camera = gameboard.camera;

        updateWindowView();
        updateWindowStats();
        updateWindowMessages();
        
    }
    
    //Updates the board to reflect changes
    private void updateView() {

        updateWindowView();
        updateWindowStats();
        updateWindowMessages();

    }
    
    private void updateGame() {
        
        gameboard.update();
        
    }
    
    private void updateWindowStats() {
        
        //Initialize variables
        
        final int statsBoardLength = 32;
        int i = 0;
        
        StyledDocument doc = jStatsBoard.getStyledDocument();
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setBackground(set, Color.BLACK);
        StyleConstants.setForeground(set, Color.WHITE);
        
        String s = "";
        
        //Upper border
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        i++;
        
        //FORMATING PLAYER NAME AND CLASS UI
        
        s += "\n " + player.objectName + " (" + player.classLevel + ")";
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        i++;

        //FORMATING LINE BREAK
        
        s += "\n";
        
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        i++;
        
        //FORMATING PLAYER HEALTH UI
        
        s += "\n Hitpoints: " + player.hitPoints + " / " + player.maxHitPoints;
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        //FORMATING LINE BREAK
        
        i++;
        
        s += "\n";
        
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        //FORMATING PLAYER WALLET UI
        
        i++;
        
        s += "\n Balance: $" + player.walletBalance;
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        //FORMATING LINE BREAK
        
        i++;
        
        s += "\n";
        
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        //FORMATING PLAYER STATS UI
        
        s += "\n STR: " + player.statStrength;
        while (s.length() < statsBoardLength * (i + 1) + i + 17) {s += " ";}
        i++;
        s += "DEX: " + player.statDexterity;
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        s += "\n CON: " + player.statConstitution;
        while (s.length() < statsBoardLength * (i + 1) + i + 17) {s += " ";}
        i++;
        s += "WIS: " + player.statWisdom;
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        s += "\n INT: " + player.statIntelligence;
        while (s.length() < statsBoardLength * (i + 1) + i + 17) {s += " ";}
        i++;
        s += "CHA: " + player.statCharisma;
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        //FORMATING LINE BREAK
        
        i++;
        
        s += "\n";
        
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}

        //FORMATING INVENTORY UI

        for (int j = 0; j < player.inventory.inventory.length / 2; j++) {
            
            //Format first item entry in row
            
            ParentItem item = player.inventory.inventory[j];
            
            s += "\n " + (j) + ". ";

            if (item != null) {
                
                s += item.itemName;
                
            } else {
                
                s += "Empty";
                
            }
            
            while (s.length() < statsBoardLength * (i + 1) + (i) + 17) {s += " ";}
            
            i++;
            
            //Format second item entry in row
            
            item = player.inventory.inventory[j + 5];
            
            s += (j + 5) + " ";

            if (item != null) {
                
                s += item.itemName;
                
            } else {
                
                s += "Empty";
                
            }
            
            while (s.length() < statsBoardLength * (i + 1) + (i)) {s += " ";}
            
        }
        
        //FORMATING LINE BREAK
        
        i++;
        
        s += "\n";
        
        while (s.length() < statsBoardLength * (i + 1) + i) {s += " ";}
        
        jStatsBoard.setText(s);
        
        //Format message board

        for (int k = 0; k < s.length(); k++) {
            
            doc.setCharacterAttributes(k, 1, set, true);
            
        }
        
    }
    
    private void updateWindowMessages() {

        //Update message board
        
        ArrayList<String> messages = camera.GetMessages();
        
        String s = "";
        
        for (String m : messages) {
            
            s += m + "\n";
            
        }
        
        jMessageBoard.setText(s);
        
        //Format message board

        StyledDocument doc = jMessageBoard.getStyledDocument();
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setBackground(set, Color.BLACK);
        StyleConstants.setForeground(set, Color.WHITE);
        
        for (int k = 0; k < s.length(); k++) {
            
            doc.setCharacterAttributes(k, 1, set, true);
            
        }
        
    }

    private void updateWindowView() {

        //Update camera screen
        
        camera.GetView(jGameScreen);

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
        jMessageBoard.setAutoscrolls(false);
        jMessageBoard.setFocusable(false);
        jMessageBoard.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jScrollPane3.setViewportView(jMessageBoard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        player = gameboard.getPlayer();

        switch (focus) {

            case "player":
                
                switch (e.getKeyCode()) {
                    
                    case KeyEvent.VK_UP:
                        player.Move("UP");
                        updateGame();
                        break;

                    case KeyEvent.VK_DOWN:
                        player.Move("DOWN");
                        updateGame();
                        break;

                    case KeyEvent.VK_LEFT:
                        player.Move("LEFT");
                        updateGame();
                        break;

                    case KeyEvent.VK_RIGHT:
                        player.Move("RIGHT");
                        updateGame();
                        break;

                    case KeyEvent.VK_SPACE:
                        gameboard.setCursor(player.xposition, player.yposition);
                        focus = "cursor";
                        break;
                        
                    case KeyEvent.VK_D:
                        if (player.inventory.isEmpty()) {
                            
                            gameboard.camera.AddMessage("You have no items to drop!");
                            
                        } else {
                            
                            focus = "dropmenu";
                            gameboard.camera.AddMessage("Type the key of the item you  wish to drop (0-9)");
                            
                        }
                        break;

                }
            
                updateView();
                
                break;
                
            case "cursor":
                
                switch (e.getKeyCode()) {
                    
                    case KeyEvent.VK_UP:
                        gameboard.moveCursor(0, -1);
                        break;

                    case KeyEvent.VK_DOWN:
                        gameboard.moveCursor(0, 1);
                        break;

                    case KeyEvent.VK_LEFT:
                        gameboard.moveCursor(-1, 0);
                        break;

                    case KeyEvent.VK_RIGHT:
                        gameboard.moveCursor(1, 0);
                        break;

                    case KeyEvent.VK_SPACE:
                        gameboard.setCursor(-1, -1);
                        focus = "player";
                        break;

                }
            
                updateView();
                
                break;
                
            case "dropmenu":

                switch (e.getKeyCode()) {
                    
                    case KeyEvent.VK_0:
                    case KeyEvent.VK_1:
                    case KeyEvent.VK_2:
                    case KeyEvent.VK_3:
                    case KeyEvent.VK_4:
                    case KeyEvent.VK_5:
                    case KeyEvent.VK_6:
                    case KeyEvent.VK_7:
                    case KeyEvent.VK_8:
                    case KeyEvent.VK_9:
                        player.dropItem(player.xposition, player.yposition, Character.getNumericValue(e.getKeyChar()));
                        updateGame();
                        focus = "player";
                        break;

                    case KeyEvent.VK_ESCAPE:
                        focus = "player";
                        break;

                }
            
                updateView();
                
                break;
            
        }
        


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
