/*
 * Abstract class that encapsulates all game objects (walls, items, ncps)
 */
package roguelike.objects;

import java.awt.Color;
import roguelike.Board;

/**
 * @author Anthony
 */
public abstract class ParentGameObject {
    
    //Class variables
    
    public Board gameboard;
    public boolean isSolid;
    public char objectSymbol;
    public Color objectColor;
    public int xposition;
    public int yposition;
    public String objectName;
    public String objectType;
    public boolean isOpaque;
    
    //Constructor
    
    public ParentGameObject() {}
    
    public ParentGameObject(int x, int y, Board b) {
        
        this.xposition = x;
        this.yposition = y;
        this.gameboard = b;
        
        gameboard.addObjectToSquare(xposition, yposition, this);
        
    }
    
    //Class methods
    
    public void changeSquare(int x, int y) {
        
        //Remove current position in board
        gameboard.removeObjectFromSquare(this);

        //Update positional variables
        xposition = x;
        yposition = y;

        //Set to new position in board
        gameboard.addObjectToSquare(x, y, this);
        
    }
    
    public void deleteSelf() {
        
        gameboard.removeObjectFromSquare(this);

        gameboard.removeObjectFromActionList(this);
        
    }

    public boolean isSolid() {
        
        return isSolid;
        
    }
    
    //Getter methods
    
    public int getX() {
        
        return xposition;
        
    }
    
    public int getY() {
        
        return yposition;
        
    }
    
    public char getSymbol() {
        
        return objectSymbol;
        
    }
    
    public Color getColor() {
        
        return objectColor;
        
    }
    
    public String getType() {
        
        return objectType;
        
    }
    
    public boolean isType(String s) {
        
        return (s.equals(objectType));
        
    }
    
    public abstract void update();


}
