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
    
    //Constructor
    
    public GameObject(int x, int y, Board b) {
        this.xposition = x;
        this.yposition = y;
        this.gameboard = b;
        
        gameboard.SetSquare(xposition, yposition, this);
    }
    
    //Class methods
    
    public boolean IsSolid() {
        return isSolid;
    }
    
    //Getter methods
    
    public int GetX() {
        return xposition;
    }
    
    public int GetY() {
        return yposition;
    }
    
    public char GetSymbol() {
        return symbol;
    }
    
    public Color GetColor() {
        return color;
    }
    
    public abstract void Update();
}
