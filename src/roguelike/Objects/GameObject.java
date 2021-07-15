/*
 * Abstract class that encapsulates all game objects (walls, items, ncps)
 */
package roguelike.Objects;

import java.awt.Color;
import roguelike.Board;

/**
 * @author Anthony
 */
public abstract class GameObject {
    
    //Class variables
    
    public Board gameboard;
    public boolean isSolid;
    public char symbol;
    public Color color;
    public int xposition;
    public int yposition;
    public String name;
    public boolean isOpaque;
    
    //Constructor
    
    public GameObject() {}
    
    public GameObject(int x, int y, Board b) {
        this.xposition = x;
        this.yposition = y;
        this.gameboard = b;
        
        gameboard.setSquare(xposition, yposition, this);
    }
    
    //Class methods
    
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
        return symbol;
    }
    
    public Color getColor() {
        return color;
    }
    
    public abstract void update();
}
