/*
 * Abstract class that encapsulates all game objects (walls, items, ncps)
 */
package roguelike.Objects;

import roguelike.Board;

/**
 *
 * @author Anthony
 */
public abstract class GameObject {
    
    //Class variables
    Board gameboard;
    boolean isSolid;
    char symbol;
    int xposition;
    int yposition;
    
    public GameObject(int x, int y, Board b){
        this.xposition = x;
        this.yposition = y;
        this.gameboard = b;
        
        gameboard.SetSquare(xposition, yposition, this);
    }
    
    //Class methods
    public boolean IsSolid(){
        return isSolid;
    }
    
    public char GetSymbol(){
        return symbol;
    }
    
    public abstract void Update();
}
