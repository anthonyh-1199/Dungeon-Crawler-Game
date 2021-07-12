/*

 */
package roguelike.Objects.Goblin;

import java.util.Random;
import roguelike.Board;
import roguelike.Objects.Entity;
import roguelike.Objects.Player;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateIdle extends State {
    
    EntityGoblin parent;

    @Override
    public void EnterState(Entity e) {
        
        parent = (EntityGoblin) e;
        
    }

    @Override
    public void Update() {

        CheckTransitions();
        
        //Move randomly
        MoveRandomly();
        
    }

    @Override
    public void CheckTransitions() {
        
        //Check if object is dead
        if (parent.health <= 0){
            
            parent.gameboard.GetCamera().AddMessage("You've slain the goblin!");

            parent.ChangeState(parent.STATE_DEAD);
            
            return;
        }
        
        Player p = parent.gameboard.GetPlayer();
        
        if (p != null){
            
            if (!Board.CheckSightLine(parent.xposition, parent.yposition, p.GetX(), p.GetY(), parent.gameboard)){
                
                parent.gameboard.GetCamera().AddMessage("The goblin lets out a     startling scream!");
                
                parent.ChangeState(parent.STATE_CHASE);
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
        if (parent.gameboard.CheckIfSquareIsEmpty(xgoal, ygoal)) {

            //Remove current position in board
            parent.gameboard.SetSquare(parent.xposition, parent.yposition, null);
            
            //Update positional variables
            parent.xposition = xgoal;
            parent.yposition = ygoal;

            //Set to new position in board
            parent.gameboard.SetSquare(xgoal, ygoal, parent);
            
        }
        
    }
    
}
