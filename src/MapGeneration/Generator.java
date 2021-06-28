
package MapGeneration;

import roguelike.Board;
import roguelike.Objects.GameObject;
import roguelike.Objects.ObjectWall;

/**
 * @author Anthony
 */

/**
 * Parent class that contains all the functionality needed to generate rooms
 * and structures inside the game world
 */
public abstract class Generator {
    
    public Board gameboard;
    
    public Generator(Board b){
        gameboard = b;
    }
    
    public void FillBoard(){
        for (int y = 0; y < gameboard.GetSize(); y++){
            for (int x = 0; x < gameboard.GetSize(); x++){
                gameboard.SetSquare(x, y, new ObjectWall(x, y, gameboard));
            }
        }
    }
}