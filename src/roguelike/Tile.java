
package roguelike;

import roguelike.Objects.GameObject;

/**
 * @author Anthony
 */

//This class represents the individual squares that form the game board
public class Tile {
    
    GameObject object;
    boolean seen;
    
    public Tile() {
        object = null;
        seen = false;
    }
    
    //Getter methods
    
    public GameObject GetObject() {
        return object;
    }
    
    public boolean GetSeen() {
        return seen;
    }
    
    //Setter methods
    
    public void SetObject(GameObject o) {
        this.object = o;
    }
    
    public void SetSeen(boolean s) {
        this.seen = s;
    }
}
