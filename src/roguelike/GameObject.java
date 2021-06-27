/*
 * Abstract class that encapsulates all game objects (walls, items, ncps)
 */
package roguelike;

/**
 *
 * @author Anthony
 */
public abstract class GameObject {
    
    //Class variables
    boolean isSolid;
    char symbol;
    
    //Class methods
    public boolean IsSolid(){
        return isSolid;
    }
    
    public char GetSymbol(){
        return symbol;
    }
    
    public abstract void Update();
}
