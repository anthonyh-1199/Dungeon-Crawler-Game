/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import roguelike.Objects.Player;

/**
 *
 * @author Anthony
 */
public class Roguelike {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board gameboard = new Board(10);
        
        Player player = new Player(100, 5, 5, gameboard);
        
        System.out.println(gameboard.GetSquare(0, 0));
        
        System.out.println(gameboard.ToString());
    }
    
}
