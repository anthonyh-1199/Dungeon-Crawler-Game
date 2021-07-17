
package roguelike;

import java.util.*;
import roguelike.objects.ParentGameObject;

/**
 * @author Anthony

 * This class represents the individual squares that form the game board
 * The variables that comprise a square include a list of all the game objects currently occupying that square, a seen flag, and a default type for when the square is empty
**/
public class Tile {
    
    List<ParentGameObject> objects;
    boolean seen;
    String type;
    
    public Tile() {
        
        objects = new ArrayList<>();
        seen = false;
        type = "stone";
        
    }
    
    //Getter methods
    
    public List<ParentGameObject> getObjects() {
        
        return objects;
        
    }
    
    public ParentGameObject getObject(int i) {
        
        if (objects.size() < i) {
            
            return null;
            
        }
        
        return objects.get(i);
        
    }
    
    public boolean getSeen() {
        
        return seen;
        
    }
    
    //Class methods
    
    public void addObject(ParentGameObject o) {
        
        objects.add(0, o);
        
    }
    
    public boolean isEmpty() {
        
        return objects.isEmpty();
        
    }
    
    public void removeObject(ParentGameObject o) {
        
        objects.remove(o);
        
    }
    
    public void setSeen(boolean s) {
        
        this.seen = s;
        
    }
    
}
