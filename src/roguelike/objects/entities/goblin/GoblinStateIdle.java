/*

 */
package roguelike.objects.entities.goblin;

import java.util.Random;
import roguelike.Board;
import roguelike.objects.entities.ParentEntity;
import roguelike.objects.entities.player.Player;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateIdle extends State {
    
    EntityGoblin parent;

    @Override
    public void enterState(ParentEntity e) {
        
        parent = (EntityGoblin) e;
        
    }

    @Override
    public void update() {

        checkTransitions();
        
        //Move randomly
        moveRandomly();
        
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
                
                parent.targetObject = (ParentEntity)p;
                
                parent.changeState(parent.STATE_CHASE);
                
            }
            
        }
        
    }
    
    public void moveRandomly(){
        
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

            parent.changeSquare(xgoal, ygoal);
            
        }
        
    }
    
}
