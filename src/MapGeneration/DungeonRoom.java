
package MapGeneration;

/**
 * @author Anthony
 */
public class DungeonRoom {
    
    int x, y;
    boolean special;
    
    public DungeonRoom(int x, int y, boolean s) {
        
        this.x = x;
        this.y = y;
        this.special = s;
        
    }
   
    public int GetX() {
        return x;
    }
    
    public int GetY() {
        return y;
    }
    
    public boolean IsSpecial() {
        return special;
    } 
    
}
