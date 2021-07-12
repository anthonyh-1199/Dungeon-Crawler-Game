
package MapGeneration;

import java.util.Random;
import roguelike.Board;
import roguelike.Objects.Goblin.EntityGoblin;
import roguelike.Objects.Player;

/**
 * @author Anthony
 */
public class CaveGenerator extends Generator{
    
    public CaveGenerator(Board b) {
        super(b);
        
        FillBoard();
        
        GenerateCave(gameboard.GetSize() / 2, gameboard.GetSize() / 2, 780);
        
        CaveCleanup();
    }
    
    private void GenerateCave(int startx, int starty, int length){
        Random r = new Random();
        int direction;
        int x = startx;
        int y = starty;
        int i = 0;
        
        //Move around the board randomly emptying squares until the desired #
        while (i < length){
            direction = r.nextInt(4);
            if (direction == 0 && (x + 1) < (gameboard.GetSize()-1)) {
                x += 1;
            } else if (direction == 1 && (x - 1) > 0) {
                x -= 1;
            } else if (direction == 2 && (y + 1) < (gameboard.GetSize()-1)) {
                y += 1;
            } else if (direction == 3 && (y - 1) > 0) {
                y -= 1;
            }
            
            if (!gameboard.CheckIfSquareIsEmpty(x, y)){
                gameboard.SetSquare(x, y, null);
                i++;
            }
        }
        
        //Add a player
        new Player(startx, starty, gameboard, 10);
        
        //TESTING
        //Add a goblin
        new EntityGoblin(x, y, gameboard, 5);
    }
    
    //Removes stray walls from the cave to improve layout
    private void CaveCleanup(){
        
        for (int y = 1; y < gameboard.GetSize() - 1; y++){
            
            for (int x = 1; x < gameboard.GetSize() - 1; x++){
                
                if (!gameboard.CheckIfSquareIsEmpty(x, y) &&
                    gameboard.GetSquare(x, y).getClass().getSimpleName().equals("ObjectWall")) {
                    
                        //If the square has at least 3 empty neighbors, remove it
                        int emptyNeighbors = 0;
                        
                        if (gameboard.CheckIfSquareIsEmpty(x - 1, y)) emptyNeighbors++;
                        if (gameboard.CheckIfSquareIsEmpty(x + 1, y)) emptyNeighbors++;
                        if (gameboard.CheckIfSquareIsEmpty(x, y - 1)) emptyNeighbors++;
                        if (gameboard.CheckIfSquareIsEmpty(x, y + 1)) emptyNeighbors++;
                        
                        if (emptyNeighbors >= 3){
                            gameboard.SetSquare(x, y, null);
                    }
                }
            }
        }
    }
}
