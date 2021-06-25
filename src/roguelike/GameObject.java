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
public abstract class GameObject {
    //Abstract class that encapsulates all game objects (walls, items, ncps)
    
    //Class variables
    boolean isSolid;
    char symbol;
    
    //Class methods
    public abstract char GetSymbol();
    
    public abstract boolean IsSolid();
}
