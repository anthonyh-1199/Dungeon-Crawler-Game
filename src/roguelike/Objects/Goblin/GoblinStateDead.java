/*
 */
package roguelike.Objects.Goblin;

import roguelike.Objects.Entity;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateDead extends State {

    EntityGoblin parent;

    @Override
    public void enterState(Entity e) {
        
        parent = (EntityGoblin) e;
        
        parent.gameboard.getCamera().AddMessage("The goblin's been slain!");
        
        //Remove self from board
        e.gameboard.setSquare(e.xposition, e.yposition, null);
        
        //Remove self from actionList
        e.gameboard.removeObjectFromList(e);
        
    }

    @Override
    public void update() {}

    @Override
    public void checkTransitions() {}
    
}
