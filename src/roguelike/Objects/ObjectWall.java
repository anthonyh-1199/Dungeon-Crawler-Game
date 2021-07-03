/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.Objects;

import java.awt.Color;
import roguelike.Board;

/**
 *
 * @author Anthony
 */
public class ObjectWall extends GameObject{
    
    public ObjectWall(int x, int y, Board b){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        this.isSolid = true;
        this.symbol = '#';
        this.color = new Color(255, 255, 255);

    }
    
    @Override
    public void Update() {}
}
