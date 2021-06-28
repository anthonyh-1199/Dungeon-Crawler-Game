/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import roguelike.Objects.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Anthony
 */
public class KeyController implements KeyListener {
    
    private Player player;
    
    public KeyController(Player p){
        player = p;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //player.MoveUp();
                break;
            case KeyEvent.VK_DOWN:
                //player.MoveRight();
                break;
            case KeyEvent.VK_LEFT:
                //player.MoveRight();
                break;
            case KeyEvent.VK_RIGHT:
                //player.MoveRight();
                break;
        }

    }
    
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
