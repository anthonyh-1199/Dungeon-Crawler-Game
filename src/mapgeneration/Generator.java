
package mapgeneration;

import roguelike.Board;
import roguelike.objects.ObjectWall;

/**
 * @author Anthony
 */

/**
 * Parent class that contains all the functionality needed to generate rooms and structures inside the game world
 */
public abstract class Generator {
    
    public Board gameboard;
    
    public Generator(Board b){
        
        gameboard = b;
        
        FillBoard();
        
    }
    
    public void FillBoard(){
        
        for (int y = 0; y < gameboard.getSize(); y++){
            
            for (int x = 0; x < gameboard.getSize(); x++){
                
                gameboard.addObjectToSquare(x, y, new ObjectWall(x, y, gameboard));
                
            }
            
        }
        
    }
    
}