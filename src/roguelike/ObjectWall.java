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
    
    public ObjectWall(int xposition, int yposition, Board gameboard){
        isSolid = true;
        gameboard.SetObjectAtSquare(xposition, yposition, this);
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
