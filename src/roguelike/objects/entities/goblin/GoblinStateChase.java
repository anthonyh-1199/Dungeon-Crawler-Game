/*

 */
package roguelike.objects.entities.goblin;

import roguelike.Board;
import roguelike.Node;
import roguelike.objects.entities.ParentEntity;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateChase extends State {
    
    EntityGoblin parent;

    @Override
    public void enterState(ParentEntity e) {
        
        parent = (EntityGoblin) e;
        
    }

    @Override
    public void update() {

        checkTransitions();
        
        //Move to target object
        if (parent.targetObject != null){

            moveTowardsSquare(parent.targetObject.getX(), parent.targetObject.getY());
            
            //Get distance to targetObject
            double distanceToTarget = Board.calculateDistance(parent.targetObject.getX(), parent.targetObject.getY(), parent.xposition, parent.yposition);

            //If we're within melee range, attempt a melee attack
            if (distanceToTarget <= parent.meleeWeapon.getRange()) {

                parent.gameboard.getCamera().addMessage("The goblin makes an attack    at you!");
                
                parent.doMeleeAttack(parent.targetObject);

            }
            
        }
        
    }

    @Override
    public void checkTransitions() {
        
        //Check if object is dead
        if (parent.hitPoints <= 0) {

            parent.changeState(parent.STATE_DEAD);
            
        }
        
    }
    
    public void moveTowardsSquare(int xgoal, int ygoal) {
        
        Node node = Node.calculatePath(parent.xposition, parent.yposition, xgoal, ygoal, parent.gameboard);
        
        //Exit if path is impossible
        if (node == null) {
            
            return;
            
        }

        //Return the next node in the path
        while (node.parent.parent != null){
            
            node = node.parent;
            
        }

        move(node.x, node.y);
        
    }
    
    public void move(int xgoal, int ygoal){

        //If space is not occupied by a solid object, move into it
        if ((!parent.gameboard.checkIfSquareHasSolid(xgoal, ygoal))) {

            parent.changeSquare(xgoal, ygoal);
            
        }
        
    }

}
