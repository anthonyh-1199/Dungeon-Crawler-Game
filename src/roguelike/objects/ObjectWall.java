/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.objects;

import java.awt.Color;
import roguelike.Board;

/**
 *
 * @author Anthony
 */
public class ObjectWall extends ParentGameObject{
    
    public ObjectWall(int x, int y, Board b){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        isOpaque = true;
        isSolid = true;
        objectSymbol = '#';
        objectColor = new Color(255,255,255);
        objectName = "wall";
        objectType = "wall";

    }
    
    @Override
    public void update() {}

}
