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
public class ObjectWall extends GameObject{
    
    char symbol = '#';
    boolean isSolid = true;
    
    public char GetSymbol(){
        return symbol;
    }
}
