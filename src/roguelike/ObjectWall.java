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
    
    public ObjectWall(int xposition, int yposition, Board gameboard){
        
        //Set variables
        this.isSolid = true;
        this.symbol = '#';
        
        gameboard.SetObjectAtSquare(xposition, yposition, this);
    }
    
    @Override
    public void Update() {}
}
