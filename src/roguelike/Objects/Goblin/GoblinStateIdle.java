/*

 */
package roguelike.Objects.Goblin;

import java.util.Random;
import roguelike.Board;
import roguelike.Objects.Entity;
import roguelike.Objects.Player.Player;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateIdle extends State {
    
    EntityGoblin parent;

    @Override
    public void enterState(Entity e) {
        
        parent = (EntityGoblin) e;
        
    }

    @Override
    public void update() {

        checkTransitions();
        
        //Move randomly
        MoveRandomly();
        
    }

    @Override
    public void checkTransitions() {
        
        //Check if object is dead
        if (parent.hitPoints <= 0){

            parent.changeState(parent.STATE_DEAD);
            
            return;
        }
        
        Player p = parent.gameboard.getPlayer();
        
        if (p != null){
            
            if (!Board.checkSightLine(parent.xposition, parent.yposition, p.getX(), p.getY(), parent.gameboard)){
                
                parent.gameboard.getCamera().AddMessage("The goblin lets out a         startling scream!");
                
                parent.changeState(parent.STATE_CHASE);
            }
        }
    }
    
    public void MoveRandomly(){
        
        int xgoal = parent.xposition;
        int ygoal = parent.yposition;
        
        //Get square in random direction
        Random r = new Random();
        int randomDirection = r.nextInt((4) + 1);
        
        switch (randomDirection){
            case 0:
                ygoal--;
                break;
            case 1:
                ygoal++;
                break;
            case 2:
                xgoal++;
                break;
            case 3:
                xgoal--;
                break;
            default:
                break;
        }
        
        //If space is empty, move into it
        if (parent.gameboard.checkIfSquareIsEmpty(xgoal, ygoal)) {

            //Remove current position in board
            parent.gameboard.setSquare(parent.xposition, parent.yposition, null);
            
            //Update positional variables
            parent.xposition = xgoal;
            parent.yposition = ygoal;

            //Set to new position in board
            parent.gameboard.setSquare(xgoal, ygoal, parent);
            
        }
        
    }
    
}
