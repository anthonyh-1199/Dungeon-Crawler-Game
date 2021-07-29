
package mapgeneration;

import java.util.Random;
import roguelike.Board;
import roguelike.objects.entities.goblin.EntityGoblin;
import roguelike.objects.entities.player.Player;

/**
 * @author Anthony
 */
public class CaveGenerator extends Generator{
    
    public CaveGenerator(Board b) {
        
        super(b);
        
        FillBoard();

    }
    
    //Generates a cave using Perlin Noise
    //Resulting map has a border of walls to prevent out-of-bounds behavior
    public void GeneratePerlinNoise(PerlinNoise noise) {
        
        for (int x = 1; x < gameboard.getSize() - 1; x++) {
            
            for (int y = 1; y < gameboard.getSize() - 1; y++) {

                if (noise.getHeight(x, y) > 0.07 || noise.getHeight(x, y) < -0.07){

                    continue;
                    
                }

                gameboard.clearSquare(x, y);

            }
            
        }
        
    }
    
    //Generates a cave using a simple random-walk algorithm
    //GenerateCave(gameboard.getSize() / 2, gameboard.getSize() / 2, length);
    public void GenerateRandomWalk(int startx, int starty, int length){
        
        Random r = new Random();
        int direction;
        int x = startx;
        int y = starty;
        int i = 0;
        
        //Move around the board randomly emptying squares until the desired #
        while (i < length){
            
            direction = r.nextInt(4);
            
            if (direction == 0 && (x + 1) < (gameboard.getSize()-1)) {
                x += 1;
            } else if (direction == 1 && (x - 1) > 0) {
                x -= 1;
            } else if (direction == 2 && (y + 1) < (gameboard.getSize()-1)) {
                y += 1;
            } else if (direction == 3 && (y - 1) > 0) {
                y -= 1;
            }
            
            if (!gameboard.checkIfSquareIsEmpty(x, y)){

                gameboard.clearSquare(x, y);
                i++;
                
            }
            
        }
        
        //Add a player
        new Player(startx, starty, gameboard, 10);
        
        //TESTING
        //Add a goblin
        new EntityGoblin(x, y, gameboard);
        
    }
    
    //Removes stray walls from the cave to improve layout
    public void CaveCleanup(){
        
        for (int y = 1; y < gameboard.getSize() - 1; y++){
            
            for (int x = 1; x < gameboard.getSize() - 1; x++){
                
                if (!gameboard.checkIfSquareIsEmpty(x, y) &&
                    gameboard.checkIfSquareContainsObject(x, y, "wall")) {
                    
                        //If the square has at least 3 empty neighbors, remove it
                        int emptyNeighbors = 0;
                        
                        if (gameboard.checkIfSquareIsEmpty(x - 1, y)) emptyNeighbors++;
                        if (gameboard.checkIfSquareIsEmpty(x + 1, y)) emptyNeighbors++;
                        if (gameboard.checkIfSquareIsEmpty(x, y - 1)) emptyNeighbors++;
                        if (gameboard.checkIfSquareIsEmpty(x, y + 1)) emptyNeighbors++;
                        
                        if (emptyNeighbors >= 3){
                            
                            gameboard.clearSquare(x, y);
                            
                    }
                }
            }
        }
    }
}
