/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

/**
 *
 * @author Anthony
 */
public class Enemy extends GameObject{
    
    int health, xposition, yposition;
    char symbol = 'S';
    boolean isSolid = true;
    
    //Constructor
    public Enemy(int xposition, int yposition, Board gameboard, int health){
        gameboard.SetObjectAtSquare(xposition, yposition, this);
        
        this.health = health;
    }

    @Override
    public boolean IsSolid(){
        return isSolid;
    }
    
    @Override
    public char GetSymbol(){
        return symbol;
    }
    
}
