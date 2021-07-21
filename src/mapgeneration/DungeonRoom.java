
package mapgeneration;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Anthony
 */
public class DungeonRoom {
    
    //Initialize variables
    
    int x, y;
    boolean special;
    List<DungeonRoom> neighbors = new LinkedList<>();
    
    //Constructor
    
    public DungeonRoom(int x, int y, boolean s) {
        
        this.x = x;
        this.y = y;
        this.special = s;
        
    }
    
    //Setter methods
    
    public void AddNeighbor(DungeonRoom r) {
        
        neighbors.add(r);
        
    }
    
    //Getter methods
    
    public List<DungeonRoom> GetNeighbors() {
        
        return neighbors;
        
    }
    
    public boolean hasNeighborNorth() {
        
        for (DungeonRoom room : neighbors) {
            
            if (room.y < y) {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }
    
    public boolean hasNeighborSouth() {
        
        for (DungeonRoom room : neighbors) {
            
            if (room.y > y) {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }
    
    public boolean hasNeighborEast() {
        
        for (DungeonRoom room : neighbors) {
            
            if (room.x > x) {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }
    
    public boolean hasNeighborWest() {
        
        for (DungeonRoom room : neighbors) {
            
            if (room.x < x) {
                
                return true;
                
            }
            
        }
        
        return false;
        
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
